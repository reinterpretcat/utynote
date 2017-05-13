package com.utynote.test.dependencies;

import com.utynote.app.dependencies.LibModule;
import com.utynote.app.dependencies.NetModule;
import com.utynote.app.dependencies.search.SearchModule;
import com.utynote.components.search.data.geojson.SearchProcessorTest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { NetModule.class, SearchModule.class, LibModule.class })
public interface SearchComponent {
    void inject(SearchProcessorTest test);
}
