package com.utynote.components.search.data.geojson.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Properties {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("gid")
    @Expose
    var gid: String? = null
    @SerializedName("layer")
    @Expose
    var layer: String? = null
    @SerializedName("source")
    @Expose
    var source: String? = null
    @SerializedName("source_id")
    @Expose
    var sourceId: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("confidence")
    @Expose
    var confidence: Double? = null
    @SerializedName("accuracy")
    @Expose
    var accuracy: String? = null
    @SerializedName("country")
    @Expose
    var country: String? = null
    @SerializedName("country_gid")
    @Expose
    var countryGid: String? = null
    @SerializedName("country_a")
    @Expose
    var countryA: String? = null
    @SerializedName("region")
    @Expose
    var region: String? = null
    @SerializedName("region_gid")
    @Expose
    var regionGid: String? = null
    @SerializedName("county")
    @Expose
    var county: String? = null
    @SerializedName("county_gid")
    @Expose
    var countyGid: String? = null
    @SerializedName("locality")
    @Expose
    var locality: String? = null
    @SerializedName("locality_gid")
    @Expose
    var localityGid: String? = null
    @SerializedName("label")
    @Expose
    var label: String? = null
    @SerializedName("borough")
    @Expose
    var borough: String? = null
    @SerializedName("borough_gid")
    @Expose
    var boroughGid: String? = null
    @SerializedName("neighbourhood")
    @Expose
    var neighbourhood: String? = null
    @SerializedName("neighbourhood_gid")
    @Expose
    var neighbourhoodGid: String? = null
    @SerializedName("region_a")
    @Expose
    var regionA: String? = null
    @SerializedName("localadmin")
    @Expose
    var localadmin: String? = null
    @SerializedName("localadmin_gid")
    @Expose
    var localadminGid: String? = null

}
