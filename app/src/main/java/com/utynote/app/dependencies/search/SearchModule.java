package com.utynote.app.dependencies.search;

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
    private String mBaseUrl;

    public SearchModule(String baseUrl) {
        this.mBaseUrl = baseUrl;
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
        return new JsonSearchRepository(searchService);
    }

    @Provides
    @Singleton
    SearchPresenter providesPresenter(SearchRepository repository) {
        return new SearchPresenter(repository);
    }
}
