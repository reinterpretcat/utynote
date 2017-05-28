package com.utynote.components.search.data.geojson

import com.utynote.components.search.data.SearchProcessor
import com.utynote.components.search.data.SearchResult
import com.utynote.components.search.data.geojson.entities.SearchData
import com.utynote.entities.GeoCoordinate
import com.utynote.entities.Place
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.*


class JsonSearchProcessor(private val service: SearchService) : SearchProcessor() {

    private var subscription: rx.Subscription? = null
    private val subscribers: MutableList<Subscriber<in SearchResult>>

    init {
        this.subscribers = ArrayList<Subscriber<in SearchResult>>()
    }

    override fun subscribe(subscriber: Subscriber<in SearchResult>) {
        subscribers.add(subscriber)
        subscriber.onSubscribe(object : Subscription {
            override fun request(n: Long) {}

            override fun cancel() {
                subscribers.remove(subscriber)
            }
        })
    }

    override fun onSubscribe(s: Subscription) {
        s.request(java.lang.Long.MAX_VALUE)
    }

    override fun onNext(term: String) {
        if (subscription != null) {
            subscription!!.unsubscribe()
        }
        if (subscribers.isEmpty()) {
            return
        }

        subscription = service
                .search(term)
                .subscribe ( { data -> notifySubscribers(term, data)},
                             { error -> subscribers.forEach { s -> s.onError(error) }  })
    }

    override fun onError(t: Throwable) {}

    override fun onComplete() {}

    private fun notifySubscribers(term: String, data: SearchData) {
        val places = data.features!!
                .filter { f -> "Point" == f.geometry!!.type }
                .map { f -> Place(id = f.properties!!.id!!,
                                  name = f.properties!!.name!!,
                                  country = f.properties!!.country!!,
                                  coordinate = GeoCoordinate(
                                          f.geometry!!.coordinates!![1],
                                          f.geometry!!.coordinates!![0])) }

        subscribers.forEach { s -> s.onNext(SearchResult(term, places)) }
    }
}
