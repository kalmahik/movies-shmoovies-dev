package com.nikita.movies_shmoovies.posters

import com.nikita.movies_shmoovies.common.network.MoviesService

interface PostersInteractor {
    fun getMoviesUpcoming(pagination: Boolean = false): PostersPM
    fun getMoviesPopular(pagination: Boolean = false): PostersPM
    fun getMoviesTopRated(): PostersPM
    fun getTvShows(): PostersPM
    fun getPeople(): PostersPM
    //fun getMovieDetails(): List<Movie>
    fun cleanUpcoming()

    fun cleanPopular()

}

data class PostersPM(val posters: List<Poster>) {
    data class Poster(val id: String,
                      val title: String,
                      val image: String?)
}

class BasePostersInteractor(val moviesService: MoviesService) : PostersInteractor {
    var upcomingCount: Int = 1
    var popularCount: Int = 1

    override fun cleanUpcoming() {
        upcomingCount = 1
    }

    override fun cleanPopular() {
        popularCount = 1
    }

    override fun getMoviesUpcoming(pagination: Boolean): PostersPM {
        val posters = moviesService.getUpcoming(upcomingCount).results
                .map { PostersPM.Poster(it.id, it.title, it.poster_path) }
        if (pagination) upcomingCount++ else
            if (!pagination && upcomingCount == 1) upcomingCount++ else upcomingCount = 1
        return PostersPM(posters)
    }

    override fun getMoviesPopular(pagination: Boolean): PostersPM {
        val posters = moviesService.getPopular(popularCount).results
                .map { PostersPM.Poster(it.id, it.title, it.poster_path) }

        if (pagination) popularCount++ else
            if (!pagination && popularCount == 1) popularCount++ else popularCount = 1
        return PostersPM(posters)
    }

    override fun getMoviesTopRated(): PostersPM {
        val posters = moviesService.getTopRated().results
                .map { PostersPM.Poster(it.id, it.title, it.poster_path) }
        return PostersPM(posters)
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