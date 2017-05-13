package com.utynote.entities;

import android.support.annotation.NonNull;

import static com.utynote.utils.Preconditions.checkNotNull;

public final class Place {
    @NonNull
    public final String id;
    @NonNull
    public final String name;
    @NonNull
    public final String country;
    @NonNull
    public final GeoCoordinate coordinate;

    private Place(@NonNull String id,
                  @NonNull String name,
                  @NonNull String country,
                  @NonNull GeoCoordinate coordinate) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.coordinate = coordinate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !Place.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        Place other = (Place) obj;
        return id.equals(other.id) &&
                name.equals(other.name) &&
                country.equals(other.country) &&
                coordinate.equals(other.coordinate);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + id.hashCode();
        hash = 53 * hash + name.hashCode();
        hash = 53 * hash + country.hashCode();
        hash = 53 * hash + coordinate.hashCode();
        return hash;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String mId;
        private String mName;
        private String mCountry;
        private GeoCoordinate mCoordinate;

        public Builder withId(@NonNull String id) {
            mId = id;
            return this;
        }

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

        public Place build() {
            return new Place(checkNotNull(mId), checkNotNull(mName),
                    checkNotNull(mCountry), checkNotNull(mCoordinate));
        }
    }
}
