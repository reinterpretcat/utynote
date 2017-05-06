package com.utynote.app.states;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Menu;

import com.utynote.app.ContentView;

import static com.utynote.utils.Preconditions.checkNotNull;

public abstract class State {

    @Nullable private ContentView mContentView;
    @Nullable private Menu mMenu;

    public State bindContent(@NonNull ContentView contentView) {
        mContentView = contentView;
        return this;
    }

    @CallSuper
    public State bindMenu(@NonNull Menu menu) {
        mMenu = menu;
        return this;
    }

    @NonNull
    protected final ContentView getContentView() {
        return checkNotNull(mContentView, "ContentView is not bind.");
    }

    @NonNull
    protected Menu getMenu() {
        return checkNotNull(mMenu, "Menu is not bind.");
    }
}
