package com.utynote.app.dependencies.search

import com.google.gson.Gson
import com.utynote.app.AppConfig
import com.utynote.components.search.SearchPresenter
import com.utynote.components.search.data.SearchProcessor
import com.utynote.components.search.data.geojson.JsonSearchProcessor
import com.utynote.components.search.data.geojson.SearchService

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class SearchModule(private val config: AppConfig.Search) {

    init {
        if ("geojson" != config.format) {
            throw UnsupportedOperationException()
        }
    }

    @Provides
    @Singleton
    internal fun providesService(gson: Gson, okHttpClient: OkHttpClient): SearchService {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(config.server)
                .client(okHttpClient)
                .build()
                .create(SearchService::class.java)
    }

    @Provides
    @Singleton
    internal fun providesProcessor(searchService: SearchService): SearchProcessor {
        return JsonSearchProcessor(searchService)
    }

    @Provides
    @Singleton
    internal fun providesPresenter(repository: SearchProcessor): SearchPresenter {
        return SearchPresenter(repository)
    }
}
