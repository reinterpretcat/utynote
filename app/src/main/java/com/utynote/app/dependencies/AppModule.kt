package com.utynote.app.dependencies

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class AppModule(internal var application: Application) {

    @Provides
    @Singleton
    internal fun providesApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    internal fun providesSharedPreferences(application: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }
}
