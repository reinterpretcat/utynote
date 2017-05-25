package com.utynote.components.search

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import com.utynote.R
import com.utynote.components.ContentView
import javax.inject.Inject

class SearchFragment : Fragment() {

    @Inject lateinit var presenter: SearchPresenter
    private val adapter = SearchAdapter()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter.attach(object : SearchContract.View {
            override fun render(model: SearchContract.ViewModel) {
                adapter.setModel(model)
            }
        })
    }

    override fun onDetach() {
        super.onDetach()
        presenter.detach()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, bundle: Bundle?): View? {
        return inflater!!.inflate(R.layout.search_container_view, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        (view as RecyclerView).adapter = adapter
    }

    override fun onStart() {
        super.onStart()

        val panel = contentView.slidingPanel
        val toolbar = contentView.toolbar

        val metrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metrics)
        val anchor = (metrics.heightPixels - toolbar.measuredHeight) / metrics.heightPixels.toFloat()

        panel.setParallaxOffset(0)
        panel.isTouchEnabled = false
        panel.anchorPoint = anchor
        panel.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
    }

    fun onSearchTerm(term: CharSequence) {
        presenter.search(term.toString())
    }

    private val contentView: ContentView
        get() = checkNotNull(activity as ContentView)

    companion object {
        val TAG = SearchFragment::class.java.simpleName
    }
}
