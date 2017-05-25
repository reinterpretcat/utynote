package com.utynote.test.network

import com.utynote.test.io.ResourceProvider


object HttpInterceptorFactory {

    fun create(assetPath: String): HttpInterceptor {

        val headers = listOf(ResourceProvider.readString("$assetPath.headers"))
                .map { s -> s.split("\n") }
                .flatMap { s -> s }
                .map { s -> s.split(Regex(":"), 1) }
                .flatMap { s -> s }
                .toTypedArray()

        val body = ResourceProvider.readBytes("$assetPath.body")

        return HttpInterceptor(headers, null, body)
    }
}
