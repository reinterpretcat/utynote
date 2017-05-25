package com.utynote.components.search.data.geojson


import com.utynote.components.search.data.geojson.entities.SearchData

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface SearchService {
    @GET("search?")
    fun search(@Query("text") term: String): Observable<SearchData>
}
