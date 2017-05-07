package com.utynote.app.dependencies.search;

import android.support.v4.app.Fragment;

import com.utynote.app.dependencies.AppModule;
import com.utynote.app.dependencies.NetModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules={AppModule.class, NetModule.class, SearchModule.class})
public interface SearchComponent {

    void inject(Fragment fragment);
}
