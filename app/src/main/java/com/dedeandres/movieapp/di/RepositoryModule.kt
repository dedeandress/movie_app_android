package com.dedeandres.movieapp.di

import com.dedeandres.movieapp.common.HttpErrorHandler
import com.dedeandres.movieapp.common.services
import com.dedeandres.movieapp.common.sharedpref.SharedPrefs
import com.dedeandres.movieapp.data.account.api.AccountApi
import com.dedeandres.movieapp.data.account.repository.AccountRepositoryImpl
import com.dedeandres.movieapp.data.account.repository.SessionRepositoryImpl
import com.dedeandres.movieapp.domain.account.repository.AccountRepository
import com.dedeandres.movieapp.domain.account.repository.SessionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideAccountApi(
        retrofit: Retrofit
    ): AccountApi {
        return services(retrofit)
    }

    @Provides
    fun provideAccountRepository(accountApi: AccountApi, httpErrorHandler: HttpErrorHandler): AccountRepository {
        return AccountRepositoryImpl(accountApi, httpErrorHandler)
    }

    @Provides
    fun provideSessionRepository(sharedPrefs: SharedPrefs): SessionRepository {
        return SessionRepositoryImpl(sharedPrefs)
    }
}