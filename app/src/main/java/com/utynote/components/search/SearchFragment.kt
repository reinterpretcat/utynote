package com.utynote.components.search

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utynote.R
import javax.inject.Inject

class SearchFragment : Fragment() {

    @Inject lateinit var presenter: SearchPresenter
    private val adapter = SearchAdapter()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, bundle: Bundle?): View? {
        return inflater!!.inflate(R.layout.search_container_view, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        (view as RecyclerView).adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(object : SearchContract.View {
            override fun render(model: SearchContract.ViewModel) {
                adapter.setModel(model)
            }
        })
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }

    fun onSearchTerm(term: CharSequence) {
        presenter.search(term.toString())
    }

    companion object {
        val TAG = SearchFragment::class.java.simpleName
    }
}
