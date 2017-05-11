package com.utynote.test.dependencies;

import android.support.annotation.NonNull;

import com.utynote.app.dependencies.NetModule;
import com.utynote.app.dependencies.search.SearchModule;
import com.utynote.test.dependencies.DaggerSearchComponent;

public final class SearchComponentFactory {

    public static SearchComponent create(@NonNull String formatType) {
        return  DaggerSearchComponent.builder()
                .netModule(new NetModule())
                .searchModule(new SearchModule("https://search.mapzen.com/v1/", formatType))
                .build();
    }
}
