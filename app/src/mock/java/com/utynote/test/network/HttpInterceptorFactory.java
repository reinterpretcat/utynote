package com.utynote.test.network;

import android.support.annotation.NonNull;

import com.utynote.test.io.AssetProvider;


public final class HttpInterceptorFactory {

    public static HttpInterceptor create(@NonNull String assetPath) {
        String headers = AssetProvider.readString(assetPath);
        byte[] body = AssetProvider.readBytes(assetPath);

        return new HttpInterceptor(null, null, body);
    }
}
