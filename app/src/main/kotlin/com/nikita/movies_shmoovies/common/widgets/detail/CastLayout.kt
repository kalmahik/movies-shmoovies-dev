package com.nikita.movies_shmoovies.common.widgets.detail

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.nikita.movies_shmoovies.R
import com.nikita.movies_shmoovies.common.utils.layoutInflater
import com.nikita.movies_shmoovies.moviedetail.Credits
import com.nikita.movies_shmoovies.movies.adapters.CastAdapter
import kotlinx.android.synthetic.main.movie_detail_cast_layout.view.*

class CastLayout : LinearLayout {

    private val adapter = CastAdapter()

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }

    private fun initView(context: Context?) {
        context!!.layoutInflater.inflate(R.layout.movie_detail_cast_layout, this)
        cast_rv.layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
    }

    fun setCrewItems(castItems: List<Credits.Cast>) {
        if (castItems.isEmpty()) {
            visibility = View.GONE
            return
        }
        adapter.data = castItems
        cast_rv.adapter = adapter
    }
}