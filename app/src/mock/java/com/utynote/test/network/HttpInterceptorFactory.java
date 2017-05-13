package com.utynote.test.network;

import android.support.annotation.NonNull;

import com.utynote.test.io.ResourceProvider;
import com.utynote.utils.Sequences;

import java.util.ArrayList;

import io.reactivex.Observable;


public final class HttpInterceptorFactory {

    public static HttpInterceptor create(@NonNull String assetPath) {
        // Split headers file content into string array using '\n' and first ':' in line
        String[] headers = Observable.just(ResourceProvider.readString(assetPath + ".headers"))
                .map(s -> s.split("\n"))
                .scan(new ArrayList<String>(), Sequences::merge)
                .flatMap(Observable::fromIterable)
                .map(s -> s.split(":", 1))
                .reduce(new ArrayList<String>(), Sequences::merge)
                .blockingGet()
                .toArray(new String[0]);

        byte[] body = ResourceProvider.readBytes(assetPath + ".body");

        return new HttpInterceptor(headers, null, body);
    }
}
