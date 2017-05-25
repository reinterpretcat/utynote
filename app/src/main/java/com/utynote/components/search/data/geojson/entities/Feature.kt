package com.utynote.components.search.data.geojson.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Feature {

    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("geometry")
    @Expose
    var geometry: Geometry? = null
    @SerializedName("properties")
    @Expose
    var properties: Properties? = null
    @SerializedName("bbox")
    @Expose
    var bbox: List<Double>? = null

}
