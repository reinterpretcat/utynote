package com.utynote.components.search.data;


import android.support.annotation.NonNull;

import com.utynote.entities.Place;

public final class SearchResult {

    @NonNull public final String term;
    @NonNull public final Iterable<Place> places;

    public SearchResult(@NonNull String term, @NonNull Iterable<Place> places) {
        this.term = term;
        this.places = places;
    }
}
