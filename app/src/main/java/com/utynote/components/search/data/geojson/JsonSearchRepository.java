package com.utynote.components.search.data.geojson;

import android.support.annotation.NonNull;

import com.utynote.components.search.data.SearchRepository;
import com.utynote.components.search.data.SearchResult;

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
                .map(data -> SearchResult.getBuilder().build());
    }
}
