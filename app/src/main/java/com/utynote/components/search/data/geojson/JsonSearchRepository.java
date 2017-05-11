package com.utynote.components.search.data.geojson;

import android.support.annotation.NonNull;

import com.utynote.components.search.data.SearchRepository;
import com.utynote.components.search.data.SearchResult;
import com.utynote.entities.GeoCoordinate;

import rx.Observable;

public class JsonSearchRepository implements SearchRepository {

    private SearchService mService;

    public JsonSearchRepository(@NonNull SearchService service) {
        mService = service;
    }

    @NonNull
    @Override
    public Observable<SearchResult> search(@NonNull String term) {
        return mService
                .search(term)
                .flatMap(data -> Observable.from(data.features))
                .filter(f -> "Point".equals(f.geometry.type))
                .map(f -> SearchResult.getBuilder()
                        .withId(f.properties.id)
                        .withName(f.properties.name)
                        .withCountry(f.properties.country)
                        .withGeoCoordinate(new GeoCoordinate(f.geometry.coordinates.get(1),
                                                             f.geometry.coordinates.get(0)))
                        .build());
    }
}
