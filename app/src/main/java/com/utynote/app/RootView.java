package com.utynote.app;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import com.utynote.components.search.SearchFragment;
import com.utynote.widgets.panel.SlidingUpPanelLayout;

/**
 * Specifies behavior of application root view which knows about all included components.
 **/
public interface RootView extends SearchFragment.Controller {
    @NonNull
    DrawerLayout getDrawerLayout();

    @NonNull
    NavigationView getNavigationView();

    @NonNull
    SlidingUpPanelLayout getSlidingPanel();
}
