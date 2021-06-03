package com.dedeandres.scaffoldprojectmvvmcoroutines.di

import android.content.Context
import com.dedeandres.scaffoldprojectmvvmcoroutines.BuildConfig
import com.dedeandres.scaffoldprojectmvvmcoroutines.common.*
import com.dedeandres.scaffoldprojectmvvmcoroutines.common.sharedpref.SharedPrefs
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class, ActivityComponent::class)
class NetworkModule {
    @Provides
    fun provideOkHttpClient(interceptors: ArrayList<Interceptor>): OkHttpClient {
        val clientBuilder = getHttpClientBuilder(interceptors)

        interceptors.map {
            clientBuilder.addInterceptor(it)
        }

        return clientBuilder.build()
    }

    @Provides
    fun provideInterceptors(
        @ApplicationContext context: Context,
        sharedPrefs: SharedPrefs
    ): ArrayList<Interceptor> {
        val interceptor = ArrayList<Interceptor>()

        interceptor.add(AuthInterceptor(context, sharedPrefs))

        return interceptor
    }

    @Provides
    fun provideGson(): Gson {
        return getGson()
    }

    @Provides
    fun httpErrorHandler(): HttpErrorHandler {
        return HttpErrorHandler()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return getRetrofit(okHttpClient, BuildConfig.API_BASE_URL)
    }
}