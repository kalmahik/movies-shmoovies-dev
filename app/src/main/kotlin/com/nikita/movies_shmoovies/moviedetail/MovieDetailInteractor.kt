package com.nikita.movies_shmoovies.moviedetail

import com.nikita.movies_shmoovies.common.network.MoviesService

interface MovieDetailInteractor {
    fun getMovieDetail(movieId: String, language: String, appendedOption: String): MoviePM
}

data class MoviePM(val movieDetail: MovieDetail, val certification: String) {
    data class MovieDetail(val id: String,
                           val backdrop_path: String,
                           val title: String,
                           val poster_path: String,
                           val release_date: String,
                           val overview: String,
                           val vote_average: Float,
                           val homepage: String,
                           val original_title: String,
                           val budget: Int,
                           val revenue: Int,
                           val runtime: Int,
                           val status: String,
                           val credits: Credits,
                           val genres: List<Genre>)

    data class Genre(val name: String)
}

data class Credits(val crew: List<Crew>, val cast: List<Cast>) {
    data class Cast(val character: String,
                    val name: String,
                    val profile_path: String)

    data class Crew(val name: String,
                    val job: String)
}

data class ReleaseDates(val release_dates: List<Date>) {
    data class Date(val certification: String,
                    val iso_639_1: String)
}

class BaseMovieDetailInteractor(val moviesService: MoviesService) : MovieDetailInteractor {
    override fun getMovieDetail(movieId: String, language: String, appendedOption: String): MoviePM {
        moviesService.getDetailMovie(movieId, language, appendedOption)
                .let {
                    return MoviePM(it, getReleaseDates(movieId, language))
                }
    }

    private fun getReleaseDates(movieId: String, language: String): String {
        val certification = moviesService.getReleaseDates(movieId).results
                .flatMap(ReleaseDates::release_dates)
                .filter { it.iso_639_1.equals(language, true) }
                .map { it.certification }

        if (certification.isEmpty()) {
            return ""
        } else {
            return certification.last()
        }

    }
}
