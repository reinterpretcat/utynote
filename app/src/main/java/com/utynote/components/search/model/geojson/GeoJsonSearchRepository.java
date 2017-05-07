package com.utynote.components.search.model.geojson;

import android.support.annotation.NonNull;

import com.utynote.components.search.model.SearchRepository;
import com.utynote.components.search.model.SearchResult;


import rx.Observable;

public class GeoJsonSearchRepository implements SearchRepository {
    @NonNull
    private final SearchService mService;

    public GeoJsonSearchRepository(@NonNull SearchService mService) {
        this.mService = mService;
    }

    @NonNull
    @Override
    public Observable<SearchResult> search(@NonNull String term) {
        // TODO map results.
        return mService
                .search(term)
                .map(data -> SearchResult.getBuilder()
                        .build());
    }
}
