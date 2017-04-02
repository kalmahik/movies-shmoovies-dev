package com.nikita.movies_shmoovies.posters

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nikita.movies_shmoovies.R
import com.nikita.movies_shmoovies.appModule
import com.nikita.movies_shmoovies.common.EXTRA_TYPE
import com.nikita.movies_shmoovies.common.mvp.BaseMvpFragment
import com.nikita.movies_shmoovies.common.mvp.ErrorDesc
import com.nikita.movies_shmoovies.common.utils.findView

class PostersFragment : BaseMvpFragment<PostersPM>(), PostersView {
    private val adapter = MoviesAdapter()
    private lateinit var swipeRefresh: SwipeRefreshLayout

    companion object {
        fun create(type: Type): PostersFragment {
            val fragment = PostersFragment()
            fragment.arguments = Bundle(1).apply { putString(EXTRA_TYPE, type.name) }
            return fragment
        }
    }


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

    override val layout: Int = R.layout.posters_content


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findView<RecyclerView>(R.id.recycler_view)
        swipeRefresh = view.findView<SwipeRefreshLayout>(R.id.swipe_refresh)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = adapter

        presenter.itemClickAction = { presenter.onPosterClick(it.id) }

        swipeRefresh.setOnRefreshListener({
            presenter.onRefreshTriggered()
        })

    }

    override fun setContent(content: PostersPM, pagination: Boolean) {
        super.setContent(content, pagination)
        swipeRefresh.isRefreshing = false

        if (pagination)
            presenter.data.addAll(content.posters.toMutableList()) else
            presenter.data = content.posters.toMutableList()

        adapter.notifyDataSetChanged()

    }

    override fun switchToError(errorDesc: ErrorDesc) {
        super.switchToError(errorDesc)
        swipeRefresh.isRefreshing = false
    }

    override fun switchToLoading(pullToRefresh: Boolean) {
        super.switchToLoading(false)
    }

    enum class Type {
        MoviesUpcoming, MoviesPopular, MoviesTopRated, TvShows, People
    }

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findView<ImageView>(R.id.poster_image)
    }

    inner class MoviesAdapter : RecyclerView.Adapter<MoviesViewHolder>() {
        override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
            presenter.onBindViewHolder(holder, position)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
            return presenter.onCreateViewHolder(parent, viewType)
        }

        override fun getItemCount() = presenter.getItemCount()
    }

}