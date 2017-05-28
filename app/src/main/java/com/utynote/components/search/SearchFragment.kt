package com.utynote.components.search

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utynote.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class SearchFragment : Fragment() {

    @Inject lateinit var presenter: SearchPresenter
    private val adapter = SearchAdapter()

    private lateinit var subscription : Disposable

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, bundle: Bundle?): View? {
        return inflater!!.inflate(R.layout.search_container_view, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        (view as RecyclerView).adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        subscription = Observable
                            .fromPublisher(presenter)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe{ it -> adapter.setModel(it) }
    }

    override fun onPause() {
        super.onPause()
        subscription.dispose()
    }

    fun onSearchTerm(term: CharSequence) {
        presenter.search(term.toString())
    }

    companion object {
        val TAG = SearchFragment::class.java.simpleName
    }
}
