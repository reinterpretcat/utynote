package com.utynote.test.dependencies

import com.utynote.app.dependencies.LibModule
import com.utynote.app.dependencies.NetModule
import com.utynote.app.dependencies.search.SearchModule
import com.utynote.components.search.SearchPresenterTest
import com.utynote.components.search.data.geojson.SearchProcessorTest

import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = arrayOf(NetModule::class, SearchModule::class, LibModule::class))
interface SearchComponent {
    fun inject(test: SearchProcessorTest)
    fun inject(test: SearchPresenterTest)
}
