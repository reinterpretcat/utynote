package com.utynote.test.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpInterceptor implements Interceptor {

    @NonNull private final String[] mHeaders;
    @Nullable private final MediaType mMediaType;
    @NonNull  private final byte[] mBody;

    public HttpInterceptor(@NonNull String[] headers,
                           @Nullable MediaType mediaType,
                           @NonNull byte[] body) {

        mHeaders = headers;
        mMediaType = mediaType;
        mBody = body;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return new Response.Builder()
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .request(chain.request())
                .headers(Headers.of(mHeaders))
                .body(ResponseBody.create(mMediaType, mBody))
                .build();
    }
}
