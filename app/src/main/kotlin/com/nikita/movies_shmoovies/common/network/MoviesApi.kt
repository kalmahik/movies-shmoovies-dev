package com.nikita.movies_shmoovies.common.network

import com.nikita.movies_shmoovies.BuildConfig
import com.nikita.movies_shmoovies.moviedetail.MoviePM
import com.nikita.movies_shmoovies.moviedetail.ReleaseDates
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {
    @GET("/3/movie/upcoming")
    fun getGetUpcoming(@Query("api_key") apiKey: String,
                       @Query("page") page: Int): Call<ListResponse<Movie>>

    @GET("/3/movie/popular")
    fun getGetPopular(@Query("api_key") apiKey: String,
                      @Query("page") page: Int): Call<ListResponse<Movie>>

    @GET("/3/movie/top_rated")
    fun getGetTopRated(@Query("api_key") apiKey: String): Call<ListResponse<Movie>>


    @GET("/3/movie/{movie_id}")
    fun getDetailMovie(@Path("movie_id") id: String,
                       @Query("api_key") apiKey: String,
                       @Query("language") language: String,
                       @Query("append_to_response") appendedOption: String): Call<MoviePM.MovieDetail>

    @GET("/3/movie/{movie_id}/release_dates")
    fun getReleaseDates(@Path("movie_id") id: String,
                        @Query("api_key") apiKey: String): Call<ListResponse<ReleaseDates>>

}

class MoviesService(api: MoviesApi) : BaseService<MoviesApi>(api) {
    fun getUpcoming(page: Int = 1) = api.getGetUpcoming(BuildConfig.API_KEY, page).executeUnsafe()
    fun getPopular(page: Int = 1) = api.getGetPopular(BuildConfig.API_KEY, page).executeUnsafe()
    fun getTopRated() = api.getGetTopRated(BuildConfig.API_KEY).executeUnsafe()
    fun getDetailMovie(movieId: String, language: String, appendedOption: String)
            = api.getDetailMovie(movieId, BuildConfig.API_KEY, language, appendedOption).executeUnsafe()

    fun getReleaseDates(movieId: String)
            = api.getReleaseDates(movieId, BuildConfig.API_KEY).executeUnsafe()


}
