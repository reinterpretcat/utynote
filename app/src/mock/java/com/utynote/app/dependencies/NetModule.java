package com.utynote.app.dependencies;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

import static com.utynote.utils.Preconditions.checkNotNull;

@Module
public class NetModule {

    private static Interceptor sInterceptor;

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(checkNotNull(sInterceptor, "NetModule is not properly configured"))
                .build();
    }

    public static void setInterceptor(@NonNull Interceptor interceptor) {
        sInterceptor = interceptor;
    }
}
