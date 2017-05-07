package com.utynote.components.search.model;


import android.support.annotation.NonNull;

import rx.Observable;

public interface SearchRepository {
    @NonNull
    Observable<SearchResult> search(@NonNull String term);
}
