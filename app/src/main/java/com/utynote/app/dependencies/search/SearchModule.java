package com.utynote.app.dependencies.search;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.utynote.app.AppConfig;
import com.utynote.components.search.SearchPresenter;
import com.utynote.components.search.data.SearchProcessor;
import com.utynote.components.search.data.geojson.JsonSearchProcessor;
import com.utynote.components.search.data.geojson.SearchService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class SearchModule {

    @NonNull
    private final AppConfig.Search mConfig;

    public SearchModule(@NonNull AppConfig.Search config) {
        if (!"geojson".equals(config.format)) {
            throw new UnsupportedOperationException();
        }
        mConfig = config;
    }

    @Provides
    @Singleton
    SearchService providesService(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(mConfig.server)
                    .client(okHttpClient)
                    .build()
                    .create(SearchService.class);
    }

    @Provides
    @Singleton
    SearchProcessor providesProcessor(SearchService searchService) {
        return new JsonSearchProcessor(searchService);
    }

    @Provides
    @Singleton
    SearchPresenter providesPresenter(SearchProcessor repository) {
        return new SearchPresenter(repository);
    }
}
