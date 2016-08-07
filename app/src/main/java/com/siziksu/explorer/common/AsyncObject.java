package com.siziksu.explorer.common;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.siziksu.explorer.common.functions.Action;
import com.siziksu.explorer.common.functions.Done;
import com.siziksu.explorer.common.functions.Fail;
import com.siziksu.explorer.common.functions.Success;

/**
 * Object used to easily create async calls.
 *
 * @param <O> the result type of the object
 */
public final class AsyncObject<O> {

    private Runnable runnable;
    private Handler handler;
    private boolean executing;
    private Action<O> action;
    private Success<O> success;
    private Fail fail;
    private Done done;
    private boolean subscribeOnMainThread;

    /**
     * Instantiates an {@code AsyncObject}
     */
    public AsyncObject() {
        // Constructor
    }

    /**
     * Subscribes the feedback functions on the main thread.
     *
     * @return {@code AsyncObject}
     */
    public AsyncObject<O> subscribeOnMainThread() {
        subscribeOnMainThread = true;
        return this;
    }

    /**
     * Gets if the object is running.
     *
     * @return true if it is running or false if it is not
     */
    public boolean isExecuting() {
        return executing;
    }

    /**
     * Sets the {@link Action} used to create the {@link Runnable} that will
     * be executed in a new {@link Thread} when the method {@link #run()}
     * is called.
     *
     * @param action the Action function that will be used
     *
     * @return {@code AsyncObject}
     */
    public AsyncObject<O> action(final Action<O> action) {
        this.action = action;
        return this;
    }

    /**
     * Sets the {@link Success} used to return the response of the
     * {@link Action} if ends successfully.
     * <br />
     * And finally executes the {@link Action}.
     *
     * @param success the Success function that will be used
     */
    public void subscribe(final Success<O> success) {
        this.success = success;
        run();
    }

    /**
     * Sets the {@link Success} used to return the response of the
     * {@link Action} if ends successfully.
     * <br />
     * Sets the {@link Error} used to return {@link Exception} that will be
     * thrown if the {@link Action} fails.
     * <br />
     * And finally executes the {@link Action}.
     *
     * @param success the Success function that will be used
     * @param fail    the Fail function that will be used
     */
    public void subscribe(final Success<O> success, final Fail fail) {
        this.success = success;
        this.fail = fail;
        run();
    }

    /**
     * Sets the {@link Done} used to emit when the response of the
     * {@link Action} if ends.
     *
     * @param done the Done function that will be used
     */
    public AsyncObject<O> done(final Done done) {
        this.done = done;
        return this;
    }

    /**
     * Executes the {@link Action} into a new {@link Thread} and gives feedback
     * if any subscription is implemented.
     */
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

    /**
     * Creates a {@link Runnable} using an {@link Action}.
     */
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
