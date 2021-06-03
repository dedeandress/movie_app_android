package com.dedeandres.movieapp.domain.genre.entity

import com.dedeandres.movieapp.presenter.movie.movielist.entity.GenreResult

data class GenreModel(val id: String, val name: String)

fun GenreModel.mapToResult(): GenreResult {
    return GenreResult(id = this.id, name = this.name)
}

fun List<GenreModel>.mapToResult(): List<GenreResult> {
    return this.map {
        it.mapToResult()
    }
}