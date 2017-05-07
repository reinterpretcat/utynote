
package com.utynote.components.search.model.geojson.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Query {

    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("size")
    @Expose
    public Integer size;
    @SerializedName("private")
    @Expose
    public Boolean _private;
    @SerializedName("lang")
    @Expose
    public Lang lang;
    @SerializedName("querySize")
    @Expose
    public Integer querySize;

}
