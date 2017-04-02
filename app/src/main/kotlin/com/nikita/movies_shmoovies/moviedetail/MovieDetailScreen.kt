package com.nikita.movies_shmoovies.moviedetail

import android.support.v4.content.ContextCompat
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nikita.movies_shmoovies.R
import com.nikita.movies_shmoovies.appModule
import com.nikita.movies_shmoovies.common.APPEND_VALUE_CREDITS
import com.nikita.movies_shmoovies.common.EXTRA_MOVIE_ID
import com.nikita.movies_shmoovies.common.LANGUAGE_EN
import com.nikita.movies_shmoovies.common.mvp.BaseMvpActivity
import com.nikita.movies_shmoovies.common.utils.findView
import com.nikita.movies_shmoovies.common.utils.load
import com.nikita.movies_shmoovies.common.widgets.CircleDisplay
import com.nikita.movies_shmoovies.common.widgets.detail.AboutLayout
import com.nikita.movies_shmoovies.common.widgets.detail.CastLayout
import com.nikita.movies_shmoovies.common.widgets.detail.CrewLayout
import com.nikita.movies_shmoovies.common.widgets.detail.OverViewLayout

class MovieDetailScreen : BaseMvpActivity<MoviePM>(), MovieView {

    override val layout: Int = R.layout.activity_movie_detail_screen

    @InjectPresenter
    lateinit var presenter: MovieDetailPresenter

    @ProvidePresenter
    fun providePresenter(): MovieDetailPresenter {
        val movieId = intent.extras.getString(EXTRA_MOVIE_ID)

        val behavior = MovieDetailBehavior(
                appModule.movieDetailInteractor,
                movieId,
                LANGUAGE_EN,
                APPEND_VALUE_CREDITS)

        return MovieDetailPresenter(behavior)
    }

    override fun initView() {
        setSupportActionBar(findView(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setTitle(R.string.movie_detail)
        initUserScore()
    }

    fun initUserScore() {
        val userScore = findView<CircleDisplay>(R.id.movie_user_score)
        userScore.setAnimDuration(500)
        userScore.setValueWidthPercent(20f)
        userScore.setTextSize(14f)
        userScore.setColor(ContextCompat.getColor(this, R.color.colorAccent))
        userScore.setDrawText(true)
        userScore.setDrawInnerCircle(true)
        userScore.setFormatDigits(0)
        userScore.setUnit("%")
    }

    fun setHeadContent(content: MoviePM) {
        findView<ImageView>(R.id.movie_detail_backdrop).load(content.movieDetail.backdrop_path)
        findView<ImageView>(R.id.movie_poster).load(content.movieDetail.poster_path)
        findView<TextView>(R.id.movie_title).text = content.movieDetail.title
        findView<TextView>(R.id.movie_release_date).text = getDateAndPg(content)
        val userScore = findView<CircleDisplay>(R.id.movie_user_score)
        userScore.visibility = View.VISIBLE
        userScore.showValue(content.movieDetail.vote_average * 10, 100f, true)
    }

    fun setOverView(bodyText: String) {
        findView<OverViewLayout>(R.id.overview_layout).setText(bodyText)
    }

    fun setCrewItems(crew: List<Credits.Crew>) {
        findView<CrewLayout>(R.id.crew_layout).setCrewItems(crew)
    }

    fun setCastItems(cast: List<Credits.Cast>) {
        findView<CastLayout>(R.id.cast_layout).setCrewItems(cast)
    }

    fun setAboutContent(content: MoviePM) {
        findView<AboutLayout>(R.id.about_layout).setAboutContent(content)
    }


    fun getDateAndPg(content: MoviePM): String {
        val dateString = content.movieDetail.release_date.split("-")[0]
        var stringFormat: String
        if (content.certification.isEmpty()) {
            return dateString
        } else {
            try {
                content.certification.toInt()
                stringFormat = getString(R.string.movie_date_and_pg_digits_format)
            } catch(e: Exception) {
                stringFormat = getString(R.string.movie_date_and_pg_chars_format)
            }
        }

        return String.format(
                stringFormat,
                dateString,
                content.certification)
    }

    override fun setContent(content: MoviePM, pagination: Boolean) {
        super.setContent(content, pagination)
        setHeadContent(content)
        setOverView(content.movieDetail.overview)
        setCrewItems(content.movieDetail.credits.crew)
        setCastItems(content.movieDetail.credits.cast)
        setAboutContent(content)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}