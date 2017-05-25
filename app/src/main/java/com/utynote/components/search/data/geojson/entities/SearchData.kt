package com.utynote.components.search.data.geojson.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchData {

    @SerializedName("geocoding")
    @Expose
    var geocoding: Geocoding? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("features")
    @Expose
    var features: List<Feature>? = null
    @SerializedName("bbox")
    @Expose
    var bbox: List<Double>? = null

}
