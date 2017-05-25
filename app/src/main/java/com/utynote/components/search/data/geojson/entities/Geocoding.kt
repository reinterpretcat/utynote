package com.utynote.components.search.data.geojson.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Geocoding {

    @SerializedName("version")
    @Expose
    var version: String? = null
    @SerializedName("attribution")
    @Expose
    var attribution: String? = null
    @SerializedName("query")
    @Expose
    var query: Query? = null
    @SerializedName("engine")
    @Expose
    var engine: Engine? = null
    @SerializedName("timestamp")
    @Expose
    var timestamp: Long = 0

}
