package com.utynote.app.dependencies.search;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.utynote.components.search.SearchPresenter;
import com.utynote.components.search.model.SearchRepository;
import com.utynote.components.search.model.geojson.JsonSearchRepository;
import com.utynote.components.search.model.geojson.SearchService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class SearchModule {

    @NonNull private final String mBaseUrl;
    @NonNull private final String mFormatType;

    @NonNull public static final String JSON_RESPONSE_TYPE = "geojson";

    public SearchModule(@NonNull String baseUrl, @NonNull String formatType) {
        mBaseUrl = baseUrl;
        mFormatType = formatType;
    }

    @Provides
    @Singleton
    SearchService providesService(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(mBaseUrl)
                    .client(okHttpClient)
                    .build()
                    .create(SearchService.class);
    }

    @Provides
    @Singleton
    SearchRepository providesRepository(SearchService searchService) {
        if (!JSON_RESPONSE_TYPE.equals(mFormatType)) {
            throw new UnsupportedOperationException();
        }
        return new JsonSearchRepository(searchService);
    }

    @Provides
    @Singleton
    SearchPresenter providesPresenter(SearchRepository repository) {
        return new SearchPresenter(repository);
    }
}
