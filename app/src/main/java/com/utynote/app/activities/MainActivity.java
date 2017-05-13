package com.utynote.app.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.jakewharton.rxbinding2.support.v4.view.RxMenuItemCompat;
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;
import com.jakewharton.rxbinding2.view.MenuItemActionViewCollapseEvent;
import com.jakewharton.rxbinding2.view.MenuItemActionViewExpandEvent;
import com.utynote.R;
import com.utynote.app.AppRoot;
import com.utynote.components.ContentView;
import com.utynote.components.map.MapFragment;
import com.utynote.components.nearby.NearbyFragment;
import com.utynote.components.search.SearchFragment;
import com.utynote.databinding.MainContentBinding;
import com.utynote.utils.Fragments;
import com.utynote.widgets.panel.SlidingUpPanelLayout;

import java.util.concurrent.TimeUnit;

import rx.Observable;

import static com.utynote.utils.Preconditions.checkNotNull;

public class MainActivity extends AppCompatActivity implements ContentView,
        NavigationView.OnNavigationItemSelectedListener {

    private Fragments mFragments;
    private MainContentBinding mContentBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFragments = new Fragments(getSupportFragmentManager());

        mContentBinding = DataBindingUtil.setContentView(this, R.layout.main_content);
        setSupportActionBar(mContentBinding.toolbar);

        mContentBinding.navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mContentBinding.drawerLayout,
                mContentBinding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mContentBinding.drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mFragments
                .addToContent(MapFragment.TAG, MapFragment::new)
                .addToPanel(NearbyFragment.TAG, NearbyFragment::new);
    }

    @Override
    public void onBackPressed() {
        if (mContentBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mContentBinding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_notes) {

        } else if (id == R.id.nav_places) {

        } else if (id == R.id.nav_calendar) {

        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        } else if (id == R.id.nav_help) {

        }

        mContentBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_appbar, menu);

        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        RxMenuItemCompat.actionViewEvents(searchMenuItem).subscribe(e -> {
                Observable.just(e)
                        .ofType(MenuItemActionViewExpandEvent.class)
                        .subscribe(expand -> mFragments.replaceInPanel(SearchFragment.TAG, this::createSearchFragment));
                Observable.just(e)
                        .ofType(MenuItemActionViewCollapseEvent.class)
                        .subscribe(collapse -> mFragments.replaceInPanel(NearbyFragment.TAG, NearbyFragment::new));
            });

        RxSearchView.queryTextChangeEvents((SearchView) searchMenuItem.getActionView())
                .filter(e -> e.queryText().length() > 2)
                .debounce(1, TimeUnit.SECONDS)
                .filter(e -> mFragments.has(SearchFragment.TAG))
                .subscribe(e -> mFragments.find(SearchFragment.TAG, SearchFragment.class).onSearchTerm(e.queryText()));

        return true;
    }

    @NonNull
    @Override
    public Toolbar getToolbar() {
        return checkNotNull(mContentBinding.toolbar);
    }

    @NonNull
    @Override
    public SlidingUpPanelLayout getSlidingPanel() {
        return checkNotNull(mContentBinding.slidingLayout);
    }

    private SearchFragment createSearchFragment() {
        SearchFragment fragment = new SearchFragment();
        ((AppRoot) getApplication()).getSearchComponent().inject(fragment);
        return fragment;
    }
}
