package com.dedeandres.movieapp.di

import android.content.Context
import android.content.SharedPreferences
import com.dedeandres.movieapp.common.sharedpref.SharedPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class, ActivityComponent::class)
class SharePrefModule {

    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SharedPrefs.PREFS_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    fun provideSharedPrefs(sharedPreferences: SharedPreferences): SharedPrefs {
        return SharedPrefs(sharedPreferences)
    }
}