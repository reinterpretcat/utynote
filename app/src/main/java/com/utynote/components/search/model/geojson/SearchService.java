package com.utynote.components.search.model.geojson;


import android.support.annotation.NonNull;

import com.utynote.components.search.model.geojson.entities.SearchData;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface SearchService {

    @GET("search?")
    @NonNull
    Observable<SearchData> search(@Query("text") String term);
}
