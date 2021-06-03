package com.dedeandres.movieapp.di.repository

import com.dedeandres.movieapp.common.HttpErrorHandler
import com.dedeandres.movieapp.common.services
import com.dedeandres.movieapp.data.genre.api.GenreApi
import com.dedeandres.movieapp.data.genre.repository.GenreRepositoryImpl
import com.dedeandres.movieapp.domain.genre.repository.GenreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class GenreRepositoryModule {

    @Provides
    fun provideGenreApi(
        retrofit: Retrofit
    ): GenreApi {
        return services(retrofit)
    }

    @Provides
    fun provideGenreRepository(
        genreApi: GenreApi,
        httpErrorHandler: HttpErrorHandler
    ): GenreRepository {
        return GenreRepositoryImpl(genreApi, httpErrorHandler)
    }
}