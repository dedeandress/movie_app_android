package com.dedeandres.movieapp.presenter.movie.moviedetail.entity

import com.dedeandres.movieapp.data.movie.entity.Cast
import com.dedeandres.movieapp.data.movie.entity.Crew

data class CastCrewResult(
    val id: Int,
    val name: String,
    val knownForDepartment: String,
    val posterPath: String
)