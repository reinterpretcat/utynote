package com.utynote.components.nearby

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import com.utynote.R
import com.utynote.components.ContentView

class NearbyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, bundle: Bundle?): View? {
        return inflater!!.inflate(R.layout.nearby_container_view, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        val viewPager = view!!.findViewById(R.id.nearby_pager) as ViewPager
        viewPager.adapter = NearbyAdapter(fragmentManager, context)

        (view.findViewById(R.id.nearby_tabs) as TabLayout).setupWithViewPager(viewPager)
    }

    override fun onStart() {
        super.onStart()

       with(contentView.slidingPanel) {
           setParallaxOffset(resources.getDimensionPixelSize(R.dimen.nearby_panel_paralax))
           isTouchEnabled = true
           anchorPoint = 0.6f
           panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
       }
    }

    private val contentView: ContentView get() = activity as ContentView

    companion object {
        val TAG = NearbyFragment::class.java.simpleName
    }
}
