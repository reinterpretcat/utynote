package com.utynote.components

import android.support.v7.widget.Toolbar

import com.utynote.widgets.panel.SlidingUpPanelLayout

interface ContentView {

    val toolbar: Toolbar

    val slidingPanel: SlidingUpPanelLayout
}
