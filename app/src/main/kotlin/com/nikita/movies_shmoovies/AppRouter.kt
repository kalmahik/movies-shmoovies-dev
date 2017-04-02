package com.nikita.movies_shmoovies

import android.content.Intent
import com.nikita.movies_shmoovies.common.EXTRA_MOVIE_ID
import com.nikita.movies_shmoovies.moviedetail.MovieDetailScreen

interface AppRouter {
    fun openMovieDetailsScreen(id: String)
    fun openTvDetailsScreen()
    fun openPersonDetailsScreen()
}

class BaseAppRouter(private val currentActivityProvider: CurrentActivityProvider) : AppRouter {
    override fun openMovieDetailsScreen(id: String) {
        val activity = currentActivityProvider.invoke()
        val intent: Intent = Intent(activity, MovieDetailScreen::class.java)
        intent.putExtra(EXTRA_MOVIE_ID, id)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        activity.startActivity(intent)
    }

    override fun openTvDetailsScreen() {
        throw UnsupportedOperationException("not implemented")
    }

    override fun openPersonDetailsScreen() {
        throw UnsupportedOperationException("not implemented")
    }
}