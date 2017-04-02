package com.nikita.movies_shmoovies.common.widgets.detail

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.nikita.movies_shmoovies.R
import com.nikita.movies_shmoovies.common.utils.layoutInflater
import com.nikita.movies_shmoovies.moviedetail.Credits
import com.nikita.movies_shmoovies.movies.adapters.CrewAdapter
import kotlinx.android.synthetic.main.movie_detail_crew_layout.view.*

class CrewLayout : LinearLayout {

    private val adapter = CrewAdapter()

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }

    private fun initView(context: Context?) {
        context!!.layoutInflater.inflate(R.layout.movie_detail_crew_layout, this)
        crew_rv.layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
    }

    fun setCrewItems(crewItems: List<Credits.Crew>) {
        if (crewItems.isEmpty()) {
            visibility = View.GONE
            return
        }
        adapter.data = crewItems
        crew_rv.adapter = adapter
    }
}