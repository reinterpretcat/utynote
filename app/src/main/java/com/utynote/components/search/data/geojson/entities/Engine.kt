package com.utynote.components.search.data.geojson.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Engine {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("author")
    @Expose
    var author: String? = null
    @SerializedName("version")
    @Expose
    var version: String? = null

}
