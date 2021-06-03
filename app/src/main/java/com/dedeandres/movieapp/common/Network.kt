package com.dedeandres.movieapp.common

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun getHttpClientBuilder(interceptors: ArrayList<Interceptor>?): OkHttpClient.Builder {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    val clientBuilder = OkHttpClient.Builder()
        .followRedirects(false)
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)

    interceptors?.forEach {
        if (it !is HttpLoggingInterceptor)
            clientBuilder.addInterceptor(it)
    }

    return clientBuilder
}

inline fun <reified T> services(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}

fun getGson(): Gson {
    return Gson().newBuilder().create()
}

fun getRetrofit(okHttpClient: OkHttpClient, url: String) : Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}