package com.utynote.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.utynote.R;
import com.utynote.app.ContentView;
import com.utynote.components.map.MapFragment;
import com.utynote.components.nearby.NearbyFragment;
import com.utynote.components.search.SearchFragment;
import com.utynote.databinding.MainContentBinding;
import com.utynote.utils.Function;
import com.utynote.widgets.panel.SlidingUpPanelLayout;

import static com.utynote.utils.Preconditions.checkNotNull;

public class MainActivity extends AppCompatActivity implements ContentView,
        NavigationView.OnNavigationItemSelectedListener {

    private MainContentBinding mContentBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContentBinding = DataBindingUtil.setContentView(this, R.layout.main_content);
        setSupportActionBar(mContentBinding.toolbar);

        mContentBinding.navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mContentBinding.drawerLayout,
                mContentBinding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mContentBinding.drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_content, new MapFragment(), MapFragment.TAG)
                .add(R.id.panel_content, new NearbyFragment(), NearbyFragment.TAG)
                .commit();
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
        MenuItemCompat.setOnActionExpandListener(searchMenuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                replaceFragment(SearchFragment.TAG, SearchFragment::new);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                replaceFragment(NearbyFragment.TAG, NearbyFragment::new);
                return true;
            }
        });

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

    private void replaceFragment(@NonNull String tag, Function.ZeroArgs<Fragment> factory) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.panel_content, fragment == null ? factory.call() : fragment, tag)
                .commit();
    }
}
