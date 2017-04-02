package com.nikita.movies_shmoovies.movies

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nikita.movies_shmoovies.MoviesAdapter
import com.nikita.movies_shmoovies.R
import com.nikita.movies_shmoovies.common.utils.findView


class MoviesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movies_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tabLayout = view.findView<TabLayout>(R.id.tab_layout)
        val viewPager = view.findView<ViewPager>(R.id.viewpager)
        viewPager.adapter = MoviesAdapter(activity.supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

}


