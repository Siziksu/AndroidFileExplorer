package com.siziksu.explorer.common.dialogs;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.View;

public class Popups {

    private static Popups instance;
    private final Context context;
    private View anchor;
    private int menu;
    private int gravity = Gravity.NO_GRAVITY;
    private PopupMenu.OnMenuItemClickListener listener;

    private Popups(Context context) {
        this.context = context;
    }

    public static Popups get(Context context) {
        if (instance == null) {
            instance = new Popups(context);
        }
        return instance;
    }

    public Popups anchor(View v) {
        this.anchor = v;
        return this;
    }

    public Popups menu(int menu) {
        this.menu = menu;
        return this;
    }

    public Popups gravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    public Popups onClickListener(PopupMenu.OnMenuItemClickListener listener) {
        this.listener = listener;
        return this;
    }

    public void show() {
        PopupMenu popup;
        popup = new PopupMenu(context, anchor, gravity);
        popup.inflate(menu);
        popup.setOnMenuItemClickListener(listener);
        popup.show();
    }
}
