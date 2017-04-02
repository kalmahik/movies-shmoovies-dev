package com.nikita.movies_shmoovies.movies.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.nikita.movies_shmoovies.R
import com.nikita.movies_shmoovies.common.utils.findView
import com.nikita.movies_shmoovies.common.utils.layoutInflater
import com.nikita.movies_shmoovies.common.utils.loadAndTransform
import com.nikita.movies_shmoovies.common.widgets.CircleTransform
import com.nikita.movies_shmoovies.moviedetail.Credits

class CastAdapter : RecyclerView.Adapter<CastHolder>() {
    var data: List<Credits.Cast> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastHolder {
        return CastHolder(parent.context.layoutInflater.inflate(R.layout.cast_item, null))
    }

    override fun onBindViewHolder(holder: CastHolder, position: Int) {
        holder.name.text = data[position].name
        holder.character.text = data[position].character
        holder.image.loadAndTransform(data[position].profile_path, CircleTransform())
    }

    override fun getItemCount() = data.size
}

class CastHolder(view: View) : RecyclerView.ViewHolder(view) {
    val image = view.findView<ImageView>(R.id.actor_photo)
    val name = view.findView<TextView>(R.id.actor_name)
    val character = view.findView<TextView>(R.id.actor_character)
}
