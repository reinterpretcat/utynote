package com.utynote.app;


import android.app.Application;
import android.support.annotation.NonNull;

import com.utynote.app.dependencies.AppModule;
import com.utynote.app.dependencies.LibModule;
import com.utynote.app.dependencies.NetModule;
import com.utynote.app.dependencies.search.DaggerSearchComponent;
import com.utynote.app.dependencies.search.SearchComponent;
import com.utynote.app.dependencies.search.SearchModule;

import static com.utynote.utils.Preconditions.checkNotNull;

public class AppRoot extends Application {
    private SearchComponent searchComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        AppConfig config = new AppConfig.Builder()
                .withSearch("https://search.mapzen.com/v1/", "geojson")
                .build();

        searchComponent = DaggerSearchComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .libModule(new LibModule())
                .searchModule(new SearchModule(config.search))
                .build();
    }

    @NonNull
    public SearchComponent getSearchComponent() {
        return checkNotNull(searchComponent);
    }
}
