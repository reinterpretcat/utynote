package com.utynote.components.search.data.geojson;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.utynote.components.search.data.SearchProcessor;
import com.utynote.components.search.data.SearchResult;
import com.utynote.components.search.data.geojson.entities.SearchData;
import com.utynote.entities.GeoCoordinate;
import com.utynote.entities.Place;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;


public class JsonSearchProcessor extends SearchProcessor {

    @Nullable private rx.Subscription mSubscription;

    @NonNull private final SearchService mService;
    @NonNull private final List<Subscriber<? super SearchResult>> mSubscribers;

    public JsonSearchProcessor(@NonNull SearchService service) {
        mService = service;
        mSubscribers = new ArrayList<>();
    }

    @Override
    public void subscribe(Subscriber<? super SearchResult> subscriber) {
        mSubscribers.add(subscriber);
        subscriber.onSubscribe(new Subscription() {
            @Override
            public void request(long n) {
            }

            @Override
            public void cancel() {
                mSubscribers.remove(subscriber);
            }
        });
    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(String term) {
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
        if (mSubscribers.isEmpty()) {
            return;
        }

        mSubscription = mService
                .search(term)
                .subscribe(data -> notifySubscribers(term, data));
    }

    @Override
    public void onError(Throwable t) {
    }

    @Override
    public void onComplete() {
    }

    private void notifySubscribers(@NonNull String term, @NonNull SearchData data) {
        Iterable<Place> places = Observable.fromIterable(data.features)
            .filter(f -> "Point".equals(f.geometry.type))
            .map(f -> Place.getBuilder()
                    .withId(f.properties.id)
                    .withName(f.properties.name)
                    .withCountry(f.properties.country)
                    .withGeoCoordinate(new GeoCoordinate(f.geometry.coordinates.get(1), f.geometry.coordinates.get(0)))
                    .build())
            .toList()
            .blockingGet();

        mSubscribers.forEach(s -> s.onNext(new SearchResult(term, places)));
    }
}
