package com.nikita.movies_shmoovies.common.widgets.detail

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.nikita.movies_shmoovies.R
import com.nikita.movies_shmoovies.common.utils.findView
import com.nikita.movies_shmoovies.common.utils.layoutInflater

class OverViewLayout : LinearLayout {

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }

    private fun initView(context: Context?) {
        context!!.layoutInflater.inflate(R.layout.movie_detail_overview_layout, this)
    }

    fun setText(bodyText: String) {
        findView<TextView>(R.id.overview_text).text = bodyText
    }
}