package com.utynote.components.search.model;


import android.support.annotation.NonNull;

import com.utynote.entities.GeoCoordinate;

import static com.utynote.utils.Preconditions.checkNotNull;

public class SearchResult {
    @NonNull
    public final String name;
    @NonNull
    public final String country;
    @NonNull
    public final GeoCoordinate coordinate;

    private SearchResult(@NonNull String name, @NonNull String country, @NonNull GeoCoordinate coordinate) {
        this.name = name;
        this.country = country;
        this.coordinate = coordinate;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String mName;
        private String mCountry;
        private GeoCoordinate mCoordinate;

        public Builder withName(@NonNull String name) {
            mName = name;
            return this;
        }

        public Builder withCountry(@NonNull String country) {
            mCountry = country;
            return this;
        }

        public Builder withGeoCoordinate(@NonNull GeoCoordinate coordinate) {
            mCoordinate = coordinate;
            return this;
        }

        public SearchResult build() {
            return new SearchResult(checkNotNull(mName), checkNotNull(mCountry), checkNotNull(mCoordinate));
        }
    }
}
