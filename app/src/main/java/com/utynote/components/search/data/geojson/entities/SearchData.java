
package com.utynote.components.search.data.geojson.entities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchData {

    @SerializedName("geocoding")
    @Expose
    public Geocoding geocoding;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("features")
    @Expose
    public List<Feature> features = null;
    @SerializedName("bbox")
    @Expose
    public List<Double> bbox = null;

}
