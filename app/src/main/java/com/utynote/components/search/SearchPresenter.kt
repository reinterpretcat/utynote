package com.utynote.components.search

import com.utynote.components.search.data.SearchProcessor
import com.utynote.components.search.data.SearchResult
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class SearchPresenter(private val processor: SearchProcessor) : SearchContract.Presenter {

    private var view: SearchContract.View? = null
    private var subscription: Disposable? = null

    override fun attach(view: SearchContract.View) {
        this.view = view
        this.subscription = Observable
                .fromPublisher(processor)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ this.onNext(it) },
                           { this.onError(it) })
    }

    override fun detach() {
        subscription!!.dispose()
        view = null
    }

    override fun search(term: String) {
        processor.onNext(term)
    }

    private fun onNext(searchResult: SearchResult) {
    view!!.render(SearchContract.ViewModel.Data(searchResult.places
                .map { (_, name, country, coordinate) -> SearchItemData(
                            primaryTitle = name,
                            secondaryTitle = "",
                            primarySubtitle = country,
                            secondarySubtitle = "${coordinate.latitude},${coordinate.longitude}") }))
    }

    private fun onError(t: Throwable) {
        view!!.render(SearchContract.ViewModel.Error(description = t.message!!))
    }
}
