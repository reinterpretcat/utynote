package com.utynote.components.search.data.geojson.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Lang {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("iso6391")
    @Expose
    var iso6391: String? = null
    @SerializedName("iso6393")
    @Expose
    var iso6393: String? = null
    @SerializedName("defaulted")
    @Expose
    var defaulted: Boolean? = null

}
