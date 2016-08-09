package com.siziksu.explorer.common;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.siziksu.explorer.common.functions.Action;
import com.siziksu.explorer.common.functions.Done;
import com.siziksu.explorer.common.functions.Fail;
import com.siziksu.explorer.common.functions.Success;

public final class AsyncObject<O> {

    private Runnable runnable;
    private Handler handler;
    private boolean executing;
    private Action<O> action;
    private Success<O> success;
    private Fail fail;
    private Done done;
    private boolean subscribeOnMainThread;

    public AsyncObject() {
        // Constructor
    }

    public AsyncObject<O> subscribeOnMainThread() {
        subscribeOnMainThread = true;
        return this;
    }

    public boolean isExecuting() {
        return executing;
    }

    public AsyncObject<O> action(final Action<O> action) {
        this.action = action;
        return this;
    }

    public void subscribe(final Success<O> success) {
        this.success = success;
        run();
    }

    public void subscribe(final Success<O> success, final Fail fail) {
        this.success = success;
        this.fail = fail;
        run();
    }

    public AsyncObject<O> done(final Done done) {
        this.done = done;
        return this;
    }

    public void run() {
        if (action != null) {
            if (subscribeOnMainThread) {
                handler = new Handler(Looper.getMainLooper());
            } else {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    handler = new Handler();
                }
            }
            Thread thread = new Thread(obtainRunnable());
            thread.setName("async-object-thread-" + thread.getId());
            thread.start();
        } else {
            throw new RuntimeException("There is no action to be executed");
        }
    }

    private Runnable obtainRunnable() {
        if (runnable == null) {
            runnable = new Runnable() {
                @Override
                public void run() {
                    executing = true;
                    try {
                        O response = action.action();
                        if (response != null && success != null) {
                            success(response);
                        } else {
                            Log.d("AsyncObject", "Action successfully completed");
                        }
                    } catch (Exception e) {
                        if (fail != null) {
                            fail(e);
                        } else {
                            Log.d("AsyncObject", e.getMessage(), e);
                        }
                    }
                    executing = false;
                    if (done != null) {
                        done();
                    }
                }
            };
        }
        return runnable;
    }

    private void success(final O response) {
        if (handler != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    success.success(response);
                }
            });
        } else {
            success.success(response);
        }
    }

    private void fail(final Exception e) {
        if (handler != null) {
            handler.post(new Runnable() {

                @Override
                public void run() {fail.fail(e);}
            });
        } else {
            fail.fail(e);
        }
    }

    private void done() {
        if (handler != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    done.done();
                }
            });
        } else {
            done.done();
        }
    }
}
