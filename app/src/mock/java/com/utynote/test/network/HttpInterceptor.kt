package com.utynote.test.network

import java.io.IOException

import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody

class HttpInterceptor(private val mHeaders: Array<String>,
                      private val mMediaType: MediaType?,
                      private val mBody: ByteArray) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return Response.Builder()
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .request(chain.request())
                .headers(Headers.of(*mHeaders))
                .body(ResponseBody.create(mMediaType, mBody))
                .build()
    }
}
