package com.utynote.test.dependencies;

import com.utynote.app.dependencies.LibModule;
import com.utynote.app.dependencies.NetModule;
import com.utynote.app.dependencies.search.SearchModule;
import com.utynote.components.search.model.geojson.SearchRepositoryTest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { NetModule.class, SearchModule.class, LibModule.class })
public interface SearchComponent {
    void inject(SearchRepositoryTest test);
}
