package com.utynote.components.search.data.geojson.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Query {

    @SerializedName("text")
    @Expose
    var text: String? = null
    @SerializedName("size")
    @Expose
    var size: Int? = null
    @SerializedName("private")
    @Expose
    var _private: Boolean? = null
    @SerializedName("lang")
    @Expose
    var lang: Lang? = null
    @SerializedName("querySize")
    @Expose
    var querySize: Int? = null

}
