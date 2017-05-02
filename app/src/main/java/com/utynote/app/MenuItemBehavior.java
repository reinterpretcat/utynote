package com.utynote.app;

import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.utynote.widgets.panel.SlidingUpPanelLayout;

/**
 * Provides the way to control the states of toolbar and sliding panel depending on
 * user interaction with specific menu items.
 */
public class MenuItemBehavior {

    private SlidingUpPanelLayout mSlidingPanel;
    private Toolbar mToolbar;

    public MenuItemBehavior withToolbar(@NonNull Toolbar toolbar) {
        mToolbar = toolbar;
        return this;
    }

    public MenuItemBehavior withSlidingPanel(@NonNull SlidingUpPanelLayout slidingPanel) {
        mSlidingPanel = slidingPanel;
        return this;
    }

    public MenuItemBehavior useSearch(@NonNull MenuItem menuItem) {
        final SearchView searchView = (SearchView) menuItem.getActionView();
        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                mSlidingPanel.setParallaxOffset(0);
                float anchor = (mSlidingPanel.getMeasuredHeight() - mToolbar.getMeasuredHeight()) /
                        (float) mSlidingPanel.getMeasuredHeight();
                mSlidingPanel.setTouchEnabled(false);
                mSlidingPanel.setAnchorPoint(anchor);
                mSlidingPanel.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // TODO use dimen
                mSlidingPanel.setTouchEnabled(true);
                mSlidingPanel.setParallaxOffset(200);
                mSlidingPanel.setAnchorPoint(0.6f);
                mSlidingPanel.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                return true;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // TODO
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return this;
    }
}
