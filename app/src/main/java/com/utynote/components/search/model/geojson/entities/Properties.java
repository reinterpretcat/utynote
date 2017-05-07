
package com.utynote.components.search.model.geojson.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Properties {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("gid")
    @Expose
    public String gid;
    @SerializedName("layer")
    @Expose
    public String layer;
    @SerializedName("source")
    @Expose
    public String source;
    @SerializedName("source_id")
    @Expose
    public String sourceId;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("confidence")
    @Expose
    public Double confidence;
    @SerializedName("accuracy")
    @Expose
    public String accuracy;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("country_gid")
    @Expose
    public String countryGid;
    @SerializedName("country_a")
    @Expose
    public String countryA;
    @SerializedName("region")
    @Expose
    public String region;
    @SerializedName("region_gid")
    @Expose
    public String regionGid;
    @SerializedName("county")
    @Expose
    public String county;
    @SerializedName("county_gid")
    @Expose
    public String countyGid;
    @SerializedName("locality")
    @Expose
    public String locality;
    @SerializedName("locality_gid")
    @Expose
    public String localityGid;
    @SerializedName("label")
    @Expose
    public String label;
    @SerializedName("borough")
    @Expose
    public String borough;
    @SerializedName("borough_gid")
    @Expose
    public String boroughGid;
    @SerializedName("neighbourhood")
    @Expose
    public String neighbourhood;
    @SerializedName("neighbourhood_gid")
    @Expose
    public String neighbourhoodGid;
    @SerializedName("region_a")
    @Expose
    public String regionA;
    @SerializedName("localadmin")
    @Expose
    public String localadmin;
    @SerializedName("localadmin_gid")
    @Expose
    public String localadminGid;

}
