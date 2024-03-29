package com.nikita.movies_shmoovies.common.utils

import android.view.View
import android.widget.ImageView
import com.nikita.movies_shmoovies.R
import com.nikita.movies_shmoovies.common.MOVIE_DB_IMAGE_BASE_URL
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation

/**
 * Can be used to remove focus from input fields on activity/controller opening
 */
fun View.stealFocus() {
  isFocusableInTouchMode = true
  isFocusable = true
  requestFocus()
}

var View.isVisible: Boolean
  get() = this.visibility == View.VISIBLE
  set(value) {
    this.visibility = if (value) View.VISIBLE else View.GONE
  }

inline fun <reified T : View> View.findView(id: Int): T = findViewById(id) as T
inline fun <reified T : View> View.findViewOptional(id: Int): T? = findViewById(id) as? T

fun ImageView.load(image: String?) = Picasso
  .with(this.context)
  .load(MOVIE_DB_IMAGE_BASE_URL + image)
  .fit()
  .centerCrop()
  .into(this)

fun ImageView.loadAndTransform(image: String?, transformWay: Transformation) = Picasso
        .with(this.context)
        .load(MOVIE_DB_IMAGE_BASE_URL + image)
        .error(R.drawable.ic_error)
        .transform(transformWay)
        .fit()
        .centerCrop()
        .into(this)