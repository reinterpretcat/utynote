package com.utynote.components.nearby

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ImageSpan

import com.utynote.R
import com.utynote.components.nearby.departures.DeparturesFragment
import com.utynote.components.nearby.live.LiveFragment
import com.utynote.components.nearby.places.PlacesFragment

internal class NearbyAdapter(fragmentManager: FragmentManager, private val context: Context) : FragmentPagerAdapter(fragmentManager) {

    private enum class PageTypes constructor(@param:DrawableRes
                                             val titleDrawableId: Int,
                                             val fragmentType: Class<*>) {
        PLACES(R.drawable.ic_nearby_places, PlacesFragment::class.java),
        DEPARTURES(R.drawable.ic_nearby_departures, DeparturesFragment::class.java),
        LIVE(R.drawable.ic_nearby_live, LiveFragment::class.java)
    }

    override fun getItem(position: Int): Fragment {
        try {
            val pageType = PageTypes.values()[position]
            return pageType.fragmentType.newInstance() as Fragment
        } catch (e: Exception) {
            throw RuntimeException("Cannot create a fragment!")
        }

    }

    override fun getPageTitle(position: Int): CharSequence {
        val pageType = PageTypes.values()[position]

        val icon = ContextCompat.getDrawable(context, pageType.titleDrawableId)
        val sb = SpannableStringBuilder(" ")
        icon.setBounds(0, 0, icon.intrinsicWidth, icon.intrinsicHeight)
        val span = ImageSpan(icon, ImageSpan.ALIGN_BASELINE)
        sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        return sb
    }

    override fun getCount(): Int {
        return PageTypes.values().size
    }
}
