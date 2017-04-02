package com.nikita.movies_shmoovies.common.widgets.detail

import android.content.Context
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.AttributeSet
import android.widget.LinearLayout
import com.nikita.movies_shmoovies.R
import com.nikita.movies_shmoovies.common.utils.layoutInflater
import com.nikita.movies_shmoovies.moviedetail.MoviePM
import com.nikita.movies_shmoovies.movies.adapters.GenresAdapter
import kotlinx.android.synthetic.main.movie_detail_about_layout.view.*

class AboutLayout : LinearLayout {

    val genreAdapter = GenresAdapter()

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)

    }

    private fun initView(context: Context?) {
        context!!.layoutInflater.inflate(R.layout.movie_detail_about_layout, this)
        genres_rv.layoutManager = StaggeredGridLayoutManager(3, VERTICAL)
    }

    fun setAboutContent(content: MoviePM) {
        setGenres(content)
        movie_status.text = content.movieDetail.status
        movie_budget.text = String.format(context.getString(R.string.movie_currency_format, content.movieDetail.budget.toString()))
        movie_revenue.text = String.format(context.getString(R.string.movie_currency_format, content.movieDetail.revenue.toString()))
        movie_runtime.text = String.format(context.getString(R.string.movie_runtime_format, content.movieDetail.runtime.toString()))
        movie_release_date.text = content.movieDetail.release_date
        movie_url.text = content.movieDetail.homepage
    }

    private fun setGenres(content: MoviePM) {
        if (!content.movieDetail.genres.isEmpty()) {
            genreAdapter.data = content.movieDetail.genres
            genres_rv.adapter = genreAdapter
        } else {
            movie_genres_title.visibility = GONE
            genres_rv.visibility = GONE
        }
    }
}