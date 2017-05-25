package com.utynote.components.search.data.geojson.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Geometry {

    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("coordinates")
    @Expose
    var coordinates: List<Double>? = null

}
