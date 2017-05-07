package com.utynote.components.search.model.geojson;

import android.support.annotation.NonNull;

import com.utynote.components.search.model.SearchRepository;
import com.utynote.components.search.model.SearchResult;

import rx.Observable;

public class JsonSearchRepository implements SearchRepository {

    private SearchService mService;

    public JsonSearchRepository(@NonNull SearchService service) {
        mService = service;
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
