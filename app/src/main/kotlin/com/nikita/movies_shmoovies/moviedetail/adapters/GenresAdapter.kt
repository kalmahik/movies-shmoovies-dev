package com.nikita.movies_shmoovies.movies.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.nikita.movies_shmoovies.R
import com.nikita.movies_shmoovies.common.utils.findView
import com.nikita.movies_shmoovies.common.utils.layoutInflater
import com.nikita.movies_shmoovies.moviedetail.MoviePM

class GenresAdapter : RecyclerView.Adapter<GenreHolder>() {
    var data: List<MoviePM.Genre> = emptyList()
    override fun onBindViewHolder(holder: GenreHolder, position: Int) {
        holder.genre.text = data[position].name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreHolder {
        return GenreHolder(parent.context.layoutInflater.inflate(R.layout.genre_item, null))
    }

    override fun getItemCount() = data.size
}

class GenreHolder(view: View) : RecyclerView.ViewHolder(view) {
    val genre = view.findView<TextView>(R.id.genre_text)
}
