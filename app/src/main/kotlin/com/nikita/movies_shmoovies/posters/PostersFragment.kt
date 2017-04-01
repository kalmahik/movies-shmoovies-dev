package com.nikita.movies_shmoovies.posters

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nikita.movies_shmoovies.MovieActivity
import com.nikita.movies_shmoovies.R
import com.nikita.movies_shmoovies.appModule
import com.nikita.movies_shmoovies.common.EXTRA_TYPE
import com.nikita.movies_shmoovies.common.mvp.BaseMvpFragment
import com.nikita.movies_shmoovies.common.utils.findView

class PostersFragment : BaseMvpFragment<PostersPM>(), PostersView {
    companion object {
        fun create(type: Type): PostersFragment {
            val fragment = PostersFragment()
            fragment.arguments = Bundle(1).apply { putString(EXTRA_TYPE, type.name) }
            return fragment
        }
    }

    override val layout: Int = R.layout.posters_content

    @InjectPresenter
    lateinit var presenter: PostersPresenter

    @ProvidePresenter
    fun providePresenter(): PostersPresenter {
        val type = Type.valueOf(arguments.getString(EXTRA_TYPE))
        val appModule = activity.appModule
        val behavior = when (type) {
            Type.MoviesUpcoming -> MovieUpcomingPostersBehavior(appModule.postersInteractor, appModule.appRouter)
            Type.MoviesPopular -> MoviePopularPostersBehavior(appModule.postersInteractor, appModule.appRouter)
            Type.MoviesTopRated -> MovieTopRatedPostersBehavior(appModule.postersInteractor, appModule.appRouter)
            Type.TvShows -> TvPostersBehavior(appModule.postersInteractor, appModule.appRouter)
            Type.People -> PeoplePostersBehavior(appModule.postersInteractor, appModule.appRouter)
        }
        return PostersPresenter(behavior)
    }

    private val adapter = PostersAdapter()

    private lateinit var swipeRefresh : SwipeRefreshLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findView<RecyclerView>(R.id.recycler_view)
        swipeRefresh = view.findView<SwipeRefreshLayout>(R.id.swipe_refresh)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = adapter
        adapter.itemClickAction = {
            val intent = Intent(this.activity, MovieActivity::class.java)
            //intent.putExtra("title", users.get(position).getName())
            //intent.putExtra("id", adapter.getItemId(1))
            startActivity(intent)
        }

        swipeRefresh.setOnRefreshListener({
            presenter.onRefreshTriggered()
        })

    }

    override fun setContent(content: PostersPM) {
        super.setContent(content)
        adapter.data = content.posters

        swipeRefresh.isRefreshing = false

    }

    enum class Type {
        MoviesUpcoming, MoviesPopular, MoviesTopRated, TvShows, People
    }

}