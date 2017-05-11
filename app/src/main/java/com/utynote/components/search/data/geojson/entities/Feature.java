
package com.utynote.components.search.data.geojson.entities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Feature {

    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("geometry")
    @Expose
    public Geometry geometry;
    @SerializedName("properties")
    @Expose
    public Properties properties;
    @SerializedName("bbox")
    @Expose
    public List<Double> bbox = null;

}
