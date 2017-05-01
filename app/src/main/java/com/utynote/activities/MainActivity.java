package com.utynote.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.utynote.R;
import com.utynote.app.RootView;
import com.utynote.components.map.MapFragment;
import com.utynote.components.nearby.NearbyFragment;
import com.utynote.components.search.SearchFragment;
import com.utynote.widgets.panel.SlidingUpPanelLayout;

public class MainActivity extends AppCompatActivity implements RootView,
        NavigationView.OnNavigationItemSelectedListener {

    private NavigationView mNavigationView;
    private SlidingUpPanelLayout mSlidingPanel;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mSlidingPanel = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_content, new MapFragment(), MapFragment.TAG)
                .add(R.id.main_content, new SearchFragment(), SearchFragment.TAG)
                .add(R.id.panel_content, new NearbyFragment(), NearbyFragment.TAG)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getDrawerLayout().isDrawerOpen(GravityCompat.START)) {
            getDrawerLayout().closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_notes) {

        } else if (id == R.id.nav_places) {

        } else if (id == R.id.nav_calendar) {

        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        } else if (id == R.id.nav_help) {

        }

        getDrawerLayout().closeDrawer(GravityCompat.START);
        return true;
    }

    @NonNull
    @Override
    public DrawerLayout getDrawerLayout() {
        return mDrawerLayout;
    }

    @NonNull
    @Override
    public NavigationView getNavigationView() {
        return mNavigationView;
    }

    @NonNull
    @Override
    public SlidingUpPanelLayout getSlidingPanel() {
        return mSlidingPanel;
    }
}
