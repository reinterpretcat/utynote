
package com.utynote.components.search.data.geojson.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geocoding {

    @SerializedName("version")
    @Expose
    public String version;
    @SerializedName("attribution")
    @Expose
    public String attribution;
    @SerializedName("query")
    @Expose
    public Query query;
    @SerializedName("engine")
    @Expose
    public Engine engine;
    @SerializedName("timestamp")
    @Expose
    public Integer timestamp;

}
