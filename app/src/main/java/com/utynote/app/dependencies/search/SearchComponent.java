package com.utynote.app.dependencies.search;

import com.utynote.app.dependencies.AppModule;
import com.utynote.app.dependencies.NetModule;
import com.utynote.components.search.SearchFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules={AppModule.class, NetModule.class, SearchModule.class})
public interface SearchComponent {
    void inject(SearchFragment fragment);
}
