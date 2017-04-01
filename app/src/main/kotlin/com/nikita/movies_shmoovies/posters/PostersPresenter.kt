package com.nikita.movies_shmoovies.posters

import com.arellomobile.mvp.InjectViewState
import com.nikita.movies_shmoovies.AppRouter
import com.nikita.movies_shmoovies.common.mvp.BaseMvpPresenter

@InjectViewState
class PostersPresenter(private val behavior: Behavior) : BaseMvpPresenter<PostersView>() {

    override fun onFirstViewAttach() = loadContent(pullToRefresh = false)

    private fun loadContent(pullToRefresh: Boolean) {
        launchLce(viewState, pullToRefresh) {
            behavior.loadContent()
        }
    }

    fun onRefreshTriggered() = loadContent(pullToRefresh = true)

    fun onPosterClick(id: String) = behavior.onPosterClick(id)

    abstract class Behavior {
        abstract fun loadContent(): PostersPM
        abstract fun onPosterClick(id: String)
    }
}

class MovieUpcomingPostersBehavior(private val postersInteractor: PostersInteractor,
                                   private val router: AppRouter) : PostersPresenter.Behavior() {
    override fun loadContent(): PostersPM = postersInteractor.getMoviesUpcoming()

    override fun onPosterClick(id: String) {
        throw UnsupportedOperationException("not implemented")
    }
}

class MoviePopularPostersBehavior(private val postersInteractor: PostersInteractor,
                                  private val router: AppRouter) : PostersPresenter.Behavior() {
    override fun loadContent(): PostersPM = postersInteractor.getMoviesPopular()

    override fun onPosterClick(id: String) {
        throw UnsupportedOperationException("not implemented")
    }
}

class MovieTopRatedPostersBehavior(private val postersInteractor: PostersInteractor,
                                   private val router: AppRouter) : PostersPresenter.Behavior() {
    override fun loadContent(): PostersPM {
        val posterTop: PostersPM = postersInteractor.getMoviesTopRated()
        return PostersPM(posterTop.posters.subList(0, 10))
    }

    override fun onPosterClick(id: String) {
        throw UnsupportedOperationException("not implemented")
    }
}

class TvPostersBehavior(private val postersInteractor: PostersInteractor,
                        private val router: AppRouter) : PostersPresenter.Behavior() {
    override fun loadContent(): PostersPM = postersInteractor.getTvShows()

    override fun onPosterClick(id: String) {
        throw UnsupportedOperationException("not implemented")
    }
}

class PeoplePostersBehavior(private val postersInteractor: PostersInteractor,
                            private val router: AppRouter) : PostersPresenter.Behavior() {
    override fun loadContent(): PostersPM = postersInteractor.getPeople()

    override fun onPosterClick(id: String) {
        throw UnsupportedOperationException("not implemented")
    }
}