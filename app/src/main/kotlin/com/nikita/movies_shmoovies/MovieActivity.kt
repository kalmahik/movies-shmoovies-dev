package com.nikita.movies_shmoovies

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.nikita.movies_shmoovies.common.network.Movie
import com.nikita.movies_shmoovies.common.utils.findView
import com.nikita.movies_shmoovies.common.utils.load
import com.nikita.movies_shmoovies.movies.MoviesFragment
import com.nikita.movies_shmoovies.posters.PostersFragment

class MovieActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.movie_details)
    setTitle(R.string.app_name)

    val image = findView<ImageView>(R.id.poster_image)
    val movieName = findView<TextView>(R.id.movie_name)
    val movie: Movie



//    image = image.load(movie.poster_path)
//    image.setImageDrawable(movie.poster_path)

   // movieName.setText(movie.title)


  }
}
