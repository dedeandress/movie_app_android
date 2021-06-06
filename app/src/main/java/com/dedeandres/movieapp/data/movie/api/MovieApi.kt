package com.dedeandres.movieapp.data.movie.api

import com.dedeandres.movieapp.data.movie.entity.MovieResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/3/movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("region") region: String
    ): MovieResponseDto

    @GET("/3/movie/top_rated")
    suspend fun getTopRated(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("region") region: String
    ): MovieResponseDto

    @GET("/3/movie/upcoming")
    suspend fun getUpcoming(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("region") region: String
    ): MovieResponseDto


}