package com.dedeandres.movieapp.domain.movie.entity

import com.dedeandres.movieapp.data.movie.entity.Cast
import com.dedeandres.movieapp.data.movie.entity.Crew
import com.dedeandres.movieapp.presenter.movie.moviedetail.entity.CastCrewResult

data class MovieCreditModel(
    val id: Int,
    val cast: List<Cast>,
    val crew: List<Crew>
)

fun MovieCreditModel.mapToResult(): List<CastCrewResult> {
    val list = ArrayList<CastCrewResult>()
    var listCast = cast.map {
        CastCrewResult(
            id = it.id,
            name = it.name,
            posterPath = it.profilePath ?: "",
            knownForDepartment = it.character
        )
    }

    var listCrew = crew.map {
        CastCrewResult(
            id = it.id,
            name = it.name,
            posterPath = it.profilePath ?: "",
            knownForDepartment = it.knownForDepartment
        )
    }

    list.addAll(listCast)
    list.add(0, listCrew[0])
    list.addAll(listCrew.subList(1, listCrew.size - 1))
    return list
}