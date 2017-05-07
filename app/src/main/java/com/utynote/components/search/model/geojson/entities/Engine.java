
package com.utynote.components.search.model.geojson.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Engine {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("author")
    @Expose
    public String author;
    @SerializedName("version")
    @Expose
    public String version;

}
