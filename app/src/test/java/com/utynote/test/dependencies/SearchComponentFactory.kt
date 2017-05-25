package com.utynote.test.dependencies

import com.utynote.app.AppConfig
import com.utynote.app.dependencies.NetModule
import com.utynote.app.dependencies.search.SearchModule

object SearchComponentFactory {

    @JvmOverloads fun create(baseUrl: String = "https://search.mapzen.com/v1/", formatType: String = "geojson"): SearchComponent {
        return DaggerSearchComponent.builder()
                .netModule(NetModule())
                .searchModule(SearchModule(AppConfig.Search( server = baseUrl, format = formatType)))
                .build()
    }
}
