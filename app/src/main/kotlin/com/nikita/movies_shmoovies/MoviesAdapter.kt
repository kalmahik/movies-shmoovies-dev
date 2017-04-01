package com.nikita.movies_shmoovies

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.nikita.movies_shmoovies.posters.PostersFragment

class MoviesAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    val PAGE_COUNT = 3
    val tabTitles = arrayOf("Ожидаемые", "Популярные", "Топ-10")


    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence {
        return tabTitles[position]
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return PostersFragment.create(PostersFragment.Type.MoviesUpcoming)
            1 -> return PostersFragment.create(PostersFragment.Type.MoviesPopular)
            2 -> return PostersFragment.create(PostersFragment.Type.MoviesTopRated)
            else -> return PostersFragment.create(PostersFragment.Type.MoviesUpcoming)
        }
    }

}

