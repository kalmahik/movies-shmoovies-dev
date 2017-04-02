package com.nikita.movies_shmoovies.moviedetail

import com.arellomobile.mvp.InjectViewState
import com.nikita.movies_shmoovies.common.mvp.BaseMvpPresenter

@InjectViewState
class MovieDetailPresenter(private val behavior: Behavior) : BaseMvpPresenter<MovieView>() {

    override fun onFirstViewAttach() = loadContent(pullToRefresh = false)

    private fun loadContent(pullToRefresh: Boolean) {
        launchLce(viewState, pullToRefresh) {
            behavior.loadContent()
        }
    }

    abstract class Behavior {
        abstract fun loadContent(): MoviePM
    }
}

class MovieDetailBehavior(private val movieDetailInteractor: MovieDetailInteractor,
                          private val movieId: String,
                          private val language: String,
                          private val appendedOption: String) : MovieDetailPresenter.Behavior() {
    override fun loadContent(): MoviePM
            = movieDetailInteractor.getMovieDetail(movieId, language, appendedOption)
}