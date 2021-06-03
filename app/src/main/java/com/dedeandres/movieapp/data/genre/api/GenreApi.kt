package com.dedeandres.movieapp.data.genre.api

import com.dedeandres.movieapp.data.genre.entity.GenreResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreApi {

    @GET("3/genre/movie/list")
    suspend fun getGenres(@Query("api_key") apiKey: String): GenreResponseDto

}