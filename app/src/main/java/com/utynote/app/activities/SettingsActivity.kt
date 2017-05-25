package com.utynote.app.activities


import android.annotation.TargetApi
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.preference.ListPreference
import android.preference.Preference
import android.preference.PreferenceActivity
import android.preference.PreferenceFragment
import android.preference.PreferenceManager
import android.support.annotation.LayoutRes
import android.support.annotation.XmlRes
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatDelegate
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup

import com.utynote.R

/**
 * A [PreferenceActivity] that presents a set of application settings.
 */
class SettingsActivity : PreferenceActivity() {

    private var delegate: AppCompatDelegate? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        getDelegate().installViewFactory()
        getDelegate().onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
        setupActionBar()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        getDelegate().onPostCreate(savedInstanceState)
    }

    val supportActionBar: ActionBar?
        get() = getDelegate().supportActionBar

    override fun getMenuInflater(): MenuInflater {
        return getDelegate().menuInflater
    }

    override fun setContentView(@LayoutRes layoutResID: Int) {
        getDelegate().setContentView(layoutResID)
    }

    override fun setContentView(view: View) {
        getDelegate().setContentView(view)
    }

    override fun setContentView(view: View, params: ViewGroup.LayoutParams) {
        getDelegate().setContentView(view, params)
    }

    override fun addContentView(view: View, params: ViewGroup.LayoutParams) {
        getDelegate().addContentView(view, params)
    }

    override fun onPostResume() {
        super.onPostResume()
        getDelegate().onPostResume()
    }

    override fun onTitleChanged(title: CharSequence, color: Int) {
        super.onTitleChanged(title, color)
        getDelegate().setTitle(title)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        getDelegate().onConfigurationChanged(newConfig)
    }

    override fun onStop() {
        super.onStop()
        getDelegate().onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        getDelegate().onDestroy()
    }

    override fun invalidateOptionsMenu() {
        getDelegate().invalidateOptionsMenu()
    }

    override fun onIsMultiPane(): Boolean {
        return isXLargeTablet
    }

    private fun getDelegate(): AppCompatDelegate {
        if (delegate == null) {
            delegate = AppCompatDelegate.create(this, null)
        }
        return delegate!!
    }

    private fun setupActionBar() {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    override fun onBuildHeaders(target: List<PreferenceActivity.Header>) {
        loadHeadersFromResource(R.xml.pref_headers, target)
    }

    override fun isValidFragment(fragmentName: String): Boolean {
        return PreferenceFragment::class.java.name == fragmentName
                || MapPreferenceFragment::class.java.name == fragmentName
                || ExperimentalPreferenceFragment::class.java.name == fragmentName
    }

    private val isXLargeTablet: Boolean
        get() = resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_XLARGE

    abstract class BasePreferenceFragment : PreferenceFragment() {
        protected abstract val summaryPreferences: Array<String>
        @get:XmlRes
        protected abstract val preferenceLayout: Int

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(preferenceLayout)
            setHasOptionsMenu(true)
            for (preference in summaryPreferences) {
                bindPreferenceSummaryToValue(findPreference(preference))
            }
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            val id = item.itemId
            if (id == android.R.id.home) {
                startActivity(Intent(activity, SettingsActivity::class.java))
                return true
            }
            return super.onOptionsItemSelected(item)
        }

        private fun bindPreferenceSummaryToValue(preference: Preference) {
            preference.onPreferenceChangeListener = mPreferenceChangeListener
            mPreferenceChangeListener.onPreferenceChange(preference,
                    PreferenceManager
                            .getDefaultSharedPreferences(preference.context)
                            .getString(preference.key, ""))
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    class MapPreferenceFragment : BasePreferenceFragment() {
        override val summaryPreferences: Array<String>
            get() = arrayOf("map_mapcss_theme")

        override val preferenceLayout: Int
            get() = R.xml.pref_map
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    class ExperimentalPreferenceFragment : BasePreferenceFragment() {
        override val summaryPreferences: Array<String>
            get() = arrayOf("exp_frame_rate_count")

        override val preferenceLayout: Int
            get() = R.xml.pref_experimental
    }

    companion object {

        private val mPreferenceChangeListener = Preference.OnPreferenceChangeListener { preference, value ->
            var stringValue: String? = value.toString()
            if (preference is ListPreference) {
                val listPreference = preference
                val index = listPreference.findIndexOfValue(stringValue)
                stringValue = if (index >= 0)
                    listPreference.entries[index].toString()
                else
                    null
            }
            preference.summary = stringValue

            true
        }
    }
}
