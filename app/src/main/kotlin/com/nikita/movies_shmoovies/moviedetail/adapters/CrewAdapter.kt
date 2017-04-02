package com.nikita.movies_shmoovies.movies.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.nikita.movies_shmoovies.R
import com.nikita.movies_shmoovies.common.utils.findView
import com.nikita.movies_shmoovies.common.utils.layoutInflater
import com.nikita.movies_shmoovies.moviedetail.Credits

class CrewAdapter : RecyclerView.Adapter<CrewMemberHolder>() {
    var data: List<Credits.Crew> = emptyList()

    override fun onBindViewHolder(holder: CrewMemberHolder, position: Int) {
        holder.name.text = data[position].name
        holder.job.text = data[position].job
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewMemberHolder {
        return CrewMemberHolder(parent.context.layoutInflater.inflate(R.layout.crew_item, null))
    }

    override fun getItemCount() = data.size
}

class CrewMemberHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name = view.findView<TextView>(R.id.crew_member_name)
    val job = view.findView<TextView>(R.id.crew_member_job)
}
