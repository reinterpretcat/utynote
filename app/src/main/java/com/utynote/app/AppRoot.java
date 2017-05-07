package com.utynote.app;


import android.app.Application;

import com.utynote.app.dependencies.AppModule;
import com.utynote.app.dependencies.NetModule;
import com.utynote.app.dependencies.search.DaggerSearchComponent;
import com.utynote.app.dependencies.search.SearchComponent;
import com.utynote.app.dependencies.search.SearchModule;

public class AppRoot extends Application {
    private SearchComponent mSearchComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mSearchComponent = DaggerSearchComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .searchModule(new SearchModule("https://search.mapzen.com/v1/"))
                .build();
    }

    public SearchComponent getSearchComponent() {
        return mSearchComponent;
    }
}
