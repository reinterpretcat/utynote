package com.utynote.app.dependencies

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class NetModule {

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(checkNotNull(sInterceptor))
                .build()
    }

    companion object {

        private var sInterceptor: Interceptor? = null

        fun setInterceptor(interceptor: Interceptor) {
            sInterceptor = interceptor
        }
    }
}
