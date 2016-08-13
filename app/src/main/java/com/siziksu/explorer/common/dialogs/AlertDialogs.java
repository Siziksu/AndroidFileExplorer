package com.siziksu.explorer.common.dialogs;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.siziksu.explorer.common.functions.Function;

public class AlertDialogs {

    private static final int NO_TITLE = -1;
    private final AppCompatActivity activity;

    public AlertDialogs(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void info(int message) {
        info(NO_TITLE, message);
    }

    public void info(int title, int message) {
        Builder builder = new Builder(activity);
        if (title != NO_TITLE) {
            builder.title(activity.getString(title));
        }
        builder.message(activity.getString(message))
               .positiveButtonText(activity.getString(android.R.string.ok))
               .show();
    }

    public void confirm(int text, final Function ok) {
        confirm(NO_TITLE, text, ok);
    }

    public void confirm(int title, int message, final Function ok) {
        Builder builder = new Builder(activity);
        if (title != NO_TITLE) {
            builder.title(activity.getString(title));
        }
        builder.message(activity.getString(message))
               .positiveButtonText(activity.getString(android.R.string.ok))
               .positiveButtonListener((dialog, which) -> ok.fun())
               .negativeButtonText(activity.getString(android.R.string.cancel))
               .show();
    }

    public void choice(int message, final Function ok, final Function cancel) {
        choice(NO_TITLE, message, ok, cancel);
    }

    public void choice(int title, int text, final Function ok, final Function cancel) {
        Builder builder = new Builder(activity);
        if (title != NO_TITLE) {
            builder.title(activity.getString(title));
        }
        builder.message(activity.getString(text))
               .positiveButtonText(activity.getString(android.R.string.ok))
               .positiveButtonListener((dialog, which) -> ok.fun())
               .negativeButtonText(activity.getString(android.R.string.cancel))
               .negativeButtonListener((dialog, which) -> cancel.fun())
               .show();
    }

    public class Builder {

        private final AppCompatActivity compatActivity;
        private String title;
        private String message;
        private String positive;
        private String negative;
        private DialogInterface.OnClickListener positiveListener;
        private DialogInterface.OnClickListener negativeListener;

        public Builder(AppCompatActivity activity) {
            this.compatActivity = activity;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder positiveButtonText(String positive) {
            this.positive = positive;
            return this;
        }

        public Builder negativeButtonText(String negative) {
            this.negative = negative;
            return this;
        }

        public Builder positiveButtonListener(DialogInterface.OnClickListener positiveListener) {
            this.positiveListener = positiveListener;
            return this;
        }

        public Builder negativeButtonListener(DialogInterface.OnClickListener negativeListener) {
            this.negativeListener = negativeListener;
            return this;
        }

        public void show() {
            AlertDialog.Builder builder = new AlertDialog.Builder(compatActivity);
            if (!TextUtils.isEmpty(title)) {
                builder.setTitle(title);
            }
            if (!TextUtils.isEmpty(message)) {
                builder.setMessage(message);
            }
            if (!TextUtils.isEmpty(positive)) {
                builder.setPositiveButton(positive, positiveListener);
            }
            if (!TextUtils.isEmpty(negative)) {
                builder.setNegativeButton(negative, negativeListener);
            }
            AlertDialog dialog = builder.create();
            if (!compatActivity.isFinishing()) {
                dialog.show();
            }
        }
    }
}
