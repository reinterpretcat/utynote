package com.utynote.components.search;

import android.support.annotation.NonNull;

import com.utynote.components.search.data.SearchRepository;

public class SearchPresenter {

    @NonNull private final SearchRepository mRepository;

    public SearchPresenter(@NonNull SearchRepository mRepository) {
        this.mRepository = mRepository;
    }
}
