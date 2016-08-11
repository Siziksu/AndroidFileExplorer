package com.siziksu.explorer.common.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

public class AlertDialogs {

    private static AlertDialogs instance;
    private final Context context;
    private String title;
    private String message;
    private String positive;
    private String negative;
    private DialogInterface.OnClickListener positiveListener;
    private DialogInterface.OnClickListener negativeListener;

    private AlertDialogs(Context context) {
        this.context = context;
    }

    public static AlertDialogs get(Context context) {
        if (instance == null) {
            instance = new AlertDialogs(context);
        }
        return instance;
    }

    public AlertDialogs title(String title) {
        this.title = title;
        return this;
    }

    public AlertDialogs message(String message) {
        this.message = message;
        return this;
    }

    public AlertDialogs positiveButtonText(String positive) {
        this.positive = positive;
        return this;
    }

    public AlertDialogs negativeButtonText(String negative) {
        this.negative = negative;
        return this;
    }

    public AlertDialogs positiveButtonListener(DialogInterface.OnClickListener positiveListener) {
        this.positiveListener = positiveListener;
        return this;
    }

    public AlertDialogs negativeButtonListener(DialogInterface.OnClickListener negativeListener) {
        this.negativeListener = negativeListener;
        return this;
    }

    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
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
        dialog.show();
    }
}
