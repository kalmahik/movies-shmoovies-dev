package com.nikita.movies_shmoovies.posters

import com.nikita.movies_shmoovies.common.network.Movie
import com.nikita.movies_shmoovies.common.network.MoviesService

interface PostersInteractor {
    fun getMoviesUpcoming(): PostersPM
    fun getMoviesPopular(): PostersPM
    fun getMoviesTopRated(): PostersPM
    fun getTvShows(): PostersPM
    fun getPeople(): PostersPM
    fun getMovieDetails(): List<Movie>
}

data class PostersPM(val posters: List<Poster>) {
    data class Poster(val id: String,
                      val title: String,
                      val image: String)
}

class BasePostersInteractor(val moviesService: MoviesService) : PostersInteractor {
    override fun getMoviesUpcoming(): PostersPM {
        val posters = moviesService.getUpcoming().results
                .map { PostersPM.Poster(it.id, it.title, it.poster_path) }
        return PostersPM(posters)
    }

    override fun getMoviesPopular(): PostersPM {
        val posters = moviesService.getPopular().results
                .map { PostersPM.Poster(it.id, it.title, it.poster_path) }
        return PostersPM(posters)
    }

    override fun getMoviesTopRated(): PostersPM {
        val posters = moviesService.getTopRated().results
                .map { PostersPM.Poster(it.id, it.title, it.poster_path) }
        return PostersPM(posters)
    }

    override fun getMovieDetails(): List<Movie> {
        val movie = moviesService.getDetails().results
                .map {
                    Movie(it.id, it.title, it.poster_path,
                            it.release_date, it.status, it.original_language,
                            it.runtime, it.budget, it.revenue, it.homepage)
                }
        return movie
    }


    override fun getTvShows(): PostersPM {
        return createFakePosters("Shows")
    }

    override fun getPeople(): PostersPM {
        return createFakePosters("People")
    }

    private fun createFakePosters(type: String): PostersPM {
        return PostersPM(List(40, { PostersPM.Poster(id = it.toString(), title = "$type$it", image = "/tUMdinO528RqibbkKIAIRoGKq0g.jpg") }))
    }
}