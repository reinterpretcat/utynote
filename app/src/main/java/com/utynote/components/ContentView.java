package com.utynote.components;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;

import com.utynote.widgets.panel.SlidingUpPanelLayout;

public interface ContentView {

    @NonNull Toolbar getToolbar();

    @NonNull SlidingUpPanelLayout getSlidingPanel();
}
