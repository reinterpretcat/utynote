package com.utynote.components.search

import com.utynote.components.search.data.SearchProcessor
import com.utynote.components.search.data.SearchResult
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.processors.BehaviorProcessor
import org.reactivestreams.Publisher
import org.reactivestreams.Subscriber

class SearchPresenter(private val processor: SearchProcessor) : Publisher<SearchViewModel> {

    private val subscription: Disposable
    private val behavior: BehaviorProcessor<SearchViewModel> = BehaviorProcessor.create()

    init {
        this.subscription = Observable
                .fromPublisher(processor)
                .subscribe({ this.onNext(it) }, { this.onError(it) })
    }

    override fun subscribe(subscriber: Subscriber<in SearchViewModel>) {
        behavior.subscribe(subscriber)
    }

    fun search(term: String) {
        behavior.onNext(SearchViewModel.Busy(term))
        processor.onNext(term)
    }

    private fun onNext(searchResult: SearchResult) {
        behavior.onNext(SearchViewModel.Data(searchResult.places
                .map { (_, name, country, coordinate) -> SearchItemData(
                        primaryTitle = name,
                        secondaryTitle = "",
                        primarySubtitle = country,
                        secondarySubtitle = "${coordinate.latitude},${coordinate.longitude}") }))
    }

    private fun onError(t: Throwable) {
        behavior.onNext(SearchViewModel.Error(description = t.message!!))
    }
}
