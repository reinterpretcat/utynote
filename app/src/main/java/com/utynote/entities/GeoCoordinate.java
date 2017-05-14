package com.utynote.entities;

public final class GeoCoordinate {

    public final double latitude;
    public final double longitude;

    public GeoCoordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !GeoCoordinate.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        GeoCoordinate other = (GeoCoordinate) obj;
        return latitude == other.latitude && longitude == other.longitude;
    }

    @Override
    public int hashCode() {
        long hash = 3;
        hash = 53 * hash + Double.doubleToLongBits(latitude);
        hash = 53 * hash + Double.doubleToLongBits(longitude);
        return Long.valueOf(hash).hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s,%s", latitude, longitude);
    }
}
