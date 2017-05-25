package com.utynote.app.dependencies.search

import com.utynote.app.dependencies.AppModule
import com.utynote.app.dependencies.LibModule
import com.utynote.app.dependencies.NetModule
import com.utynote.components.search.SearchFragment

import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = arrayOf(AppModule::class, NetModule::class, SearchModule::class, LibModule::class))
interface SearchComponent {
    fun inject(fragment: SearchFragment)
}
