
package com.utynote.components.search.model.geojson.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lang {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("iso6391")
    @Expose
    public String iso6391;
    @SerializedName("iso6393")
    @Expose
    public String iso6393;
    @SerializedName("defaulted")
    @Expose
    public Boolean defaulted;

}
