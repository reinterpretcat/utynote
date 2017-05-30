package com.utynote.app.activities

import android.content.Intent
import android.content.res.Resources
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import com.jakewharton.rxbinding2.support.v4.view.RxMenuItemCompat
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView
import com.jakewharton.rxbinding2.view.MenuItemActionViewCollapseEvent
import com.jakewharton.rxbinding2.view.MenuItemActionViewExpandEvent
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import com.utynote.R
import com.utynote.app.AppRoot
import com.utynote.components.map.MapFragment
import com.utynote.components.nearby.NearbyFragment
import com.utynote.components.search.SearchFragment
import com.utynote.databinding.MainContentBinding
import com.utynote.utils.Fragments
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private class PanelConfig (val parallax : Int,
                               val touch: Boolean,
                               val anchor: Float,
                               val state : SlidingUpPanelLayout.PanelState)

    private val navListener = NavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.nav_notes    -> { }
            R.id.nav_places   -> { }
            R.id.nav_calendar -> { }
            R.id.nav_help     -> { }
            R.id.nav_settings -> startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
        }
        binding!!.drawerLayout.closeDrawer(GravityCompat.START)
        true
    }

    private val fragments: Fragments = Fragments(supportFragmentManager)

    private var panelConfig: PanelConfig = PanelConfig(0, false, 0f, SlidingUpPanelLayout.PanelState.HIDDEN)
    private var binding: MainContentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<MainContentBinding>(this, R.layout.main_content)

        with(binding!!) {
            setSupportActionBar(toolbar)
            val toggle = ActionBarDrawerToggle(this@MainActivity, drawerLayout, toolbar,
                    R.string.navigation_drawer_open,  R.string.navigation_drawer_close)
            navigationView.setNavigationItemSelectedListener(navListener)
            drawerLayout.setDrawerListener(toggle)
            toggle.syncState()
        }

        savedInstanceState ?: fragments
                .addToContent(MapFragment.TAG, { MapFragment() })
                .addToPanel(NearbyFragment.TAG, { NearbyFragment() })
    }

    override fun onAttachFragment(fragment: Fragment?) {
        super.onAttachFragment(fragment)
        val f = fragment!!

        panelConfig = when (f) {
            is SearchFragment -> {
                (application as AppRoot).getSearchComponent().inject(f)
                PanelConfig(
                        parallax = resources.getDimensionPixelSize(R.dimen.search_panel_paralax),
                        touch = false,
                        anchor = calculateRelativeOffset(resources.getDimensionPixelSize(R.dimen.search_panel_anchor)),
                        state = SlidingUpPanelLayout.PanelState.ANCHORED)
            }
            is NearbyFragment -> {
                PanelConfig(
                        parallax = resources.getDimensionPixelSize(R.dimen.nearby_panel_paralax),
                        touch = true,
                        anchor = calculateRelativeOffset(resources.getDimensionPixelSize(R.dimen.nearby_panel_anchor)),
                        state = SlidingUpPanelLayout.PanelState.COLLAPSED)
            }
            else -> return
        }
        configurePanel()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        configurePanel()
    }

    override fun onBackPressed() {
        if (binding!!.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding!!.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_appbar, menu)

        val searchMenuItem = menu.findItem(R.id.action_search)

        RxMenuItemCompat.actionViewEvents(searchMenuItem).subscribe { e ->
            when (e) {
                is MenuItemActionViewExpandEvent   -> fragments.replaceInPanel(SearchFragment.TAG, { SearchFragment() })
                is MenuItemActionViewCollapseEvent -> fragments.replaceInPanel(NearbyFragment.TAG, { NearbyFragment() })
            }
        }

        RxSearchView.queryTextChangeEvents(searchMenuItem.actionView as SearchView)
                .filter    { e -> e.queryText().length > 2 }
                .debounce(1, TimeUnit.SECONDS)
                .filter    { fragments.isAttached(SearchFragment.TAG) }
                .subscribe { e -> fragments.find(SearchFragment.TAG, SearchFragment::class.java).onSearchTerm(e.queryText()) }

        return true
    }

    fun calculateRelativeOffset(absoluteOffset: Int) : Float {
        return (Resources.getSystem().displayMetrics.heightPixels - absoluteOffset) /
                Resources.getSystem().displayMetrics.heightPixels.toFloat()
    }

    fun configurePanel() {
        val panel = binding?.slidingLayout
        if (panel != null ) {
            with(panel) {
                setParallaxOffset(panelConfig.parallax)
                isTouchEnabled = panelConfig.touch
                anchorPoint = panelConfig.anchor
                panelState = panelConfig.state
            }
        }
    }
}
