package com.utynote.app.states;

import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.utynote.R;
import com.utynote.widgets.panel.SlidingUpPanelLayout;

public class SearchState extends State {

    private final OnActionExpandListener m_searchMenuListener = new OnActionExpandListener() {
        @Override
        public boolean onMenuItemActionExpand(MenuItem item) {
            SlidingUpPanelLayout panel = getContentView().getSlidingPanel();
            Toolbar toolbar = getContentView().getToolbar();
            float anchor = (panel.getMeasuredHeight() - toolbar.getMeasuredHeight()) /(float) panel.getMeasuredHeight();

            panel.setParallaxOffset(0);
            panel.setTouchEnabled(false);
            panel.setAnchorPoint(anchor);
            panel.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);

            return true;
        }

        @Override
        public boolean onMenuItemActionCollapse(MenuItem item) {
            SlidingUpPanelLayout panel = getContentView().getSlidingPanel();

            panel.setParallaxOffset(200);
            panel.setAnchorPoint(0.6f);
            panel.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);

            return true;
        }
    };

    @Override
    public State bindMenu(@NonNull Menu menu) {
        super.bindMenu(menu);

        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        MenuItemCompat.setOnActionExpandListener(searchMenuItem, m_searchMenuListener);

        final SearchView searchView = (SearchView) searchMenuItem.getActionView();
        //searchView.setOnQueryTextListener()

        return this;
    }
}
