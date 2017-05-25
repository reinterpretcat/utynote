package com.utynote.app.activities

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.jakewharton.rxbinding2.support.v4.view.RxMenuItemCompat
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView
import com.jakewharton.rxbinding2.view.MenuItemActionViewCollapseEvent
import com.jakewharton.rxbinding2.view.MenuItemActionViewEvent
import com.jakewharton.rxbinding2.view.MenuItemActionViewExpandEvent
import com.utynote.R
import com.utynote.app.AppRoot
import com.utynote.components.ContentView
import com.utynote.components.map.MapFragment
import com.utynote.components.nearby.NearbyFragment
import com.utynote.components.search.SearchFragment
import com.utynote.databinding.MainContentBinding
import com.utynote.utils.Fragments
import com.utynote.widgets.panel.SlidingUpPanelLayout
import rx.Observable
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity(), ContentView, NavigationView.OnNavigationItemSelectedListener {

    private var fragments: Fragments? = null
    private var binding: MainContentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<MainContentBinding>(this, R.layout.main_content)
        setSupportActionBar(binding!!.toolbar)

        binding!!.navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(this, binding!!.drawerLayout,
                binding!!.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding!!.drawerLayout.setDrawerListener(toggle)
        toggle.syncState()

        fragments = Fragments(supportFragmentManager)

        if (savedInstanceState == null) {
            fragments!!
                    .addToContent(MapFragment.TAG, { MapFragment() })
                    .addToPanel(NearbyFragment.TAG, { NearbyFragment() })
        }
    }

    override fun onBackPressed() {
        if (binding!!.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding!!.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.nav_notes) {

        } else if (id == R.id.nav_places) {

        } else if (id == R.id.nav_calendar) {

        } else if (id == R.id.nav_settings) {
            startActivity(Intent(this, SettingsActivity::class.java))
        } else if (id == R.id.nav_help) {

        }

        binding!!.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_appbar, menu)

        val searchMenuItem = menu.findItem(R.id.action_search)

        RxMenuItemCompat.actionViewEvents(searchMenuItem).subscribe { e ->
            Observable.just<MenuItemActionViewEvent>(e)
                    .ofType(MenuItemActionViewExpandEvent::class.java)
                    .subscribe { expand ->
                        Log.d("###", "MenuItemActionViewExpandEvent")
                        fragments!!.replaceInPanel(SearchFragment.TAG, { this.createSearchFragment() })
                    }
            Observable.just<MenuItemActionViewEvent>(e)
                    .ofType(MenuItemActionViewCollapseEvent::class.java)
                    .subscribe { collapse ->
                        Log.d("###", "MenuItemActionViewCollapseEvent")
                        fragments!!.replaceInPanel(NearbyFragment.TAG, { NearbyFragment() })
                    }
        }

        RxSearchView.queryTextChangeEvents(searchMenuItem.actionView as SearchView)
                .filter { e -> e.queryText().length > 2 }
                .debounce(1, TimeUnit.SECONDS)
                .filter { e -> fragments!!.isAttached(SearchFragment.TAG) }
                .subscribe { e ->
                    Log.d("###", "queryTextChangeEvents")
                    fragments!!.find(SearchFragment.TAG, SearchFragment::class.java).onSearchTerm(e.queryText())
                }

        return true
    }

    override val toolbar: Toolbar
        get() = checkNotNull(binding!!.toolbar)

    override val slidingPanel: SlidingUpPanelLayout
        get() = checkNotNull(binding!!.slidingLayout)

    private fun createSearchFragment(): SearchFragment {
        val fragment = SearchFragment()
        (application as AppRoot).getSearchComponent().inject(fragment)
        return fragment
    }
}
