package com.utynote.app


import android.app.Application

import com.utynote.app.dependencies.AppModule
import com.utynote.app.dependencies.LibModule
import com.utynote.app.dependencies.NetModule
import com.utynote.app.dependencies.search.DaggerSearchComponent
import com.utynote.app.dependencies.search.SearchComponent
import com.utynote.app.dependencies.search.SearchModule


class AppRoot : Application() {
    private var searchComponent: SearchComponent? = null

    override fun onCreate() {
        super.onCreate()

        val config = AppConfig(AppConfig.Search(
                        server = "https://search.mapzen.com/v1/",
                        format = "geojson"))

        searchComponent = DaggerSearchComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule())
                .libModule(LibModule())
                .searchModule(SearchModule(config.search))
                .build()
    }

    fun getSearchComponent(): SearchComponent {
        return checkNotNull(searchComponent)
    }
}
