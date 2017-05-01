package com.utynote.app;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import com.utynote.widgets.panel.SlidingUpPanelLayout;

/** Specifies behavior of application root view. */
public interface RootView {
    @NonNull
    DrawerLayout getDrawerLayout();

    @NonNull
    NavigationView getNavigationView();

    @NonNull
    SlidingUpPanelLayout getSlidingPanel();
}
