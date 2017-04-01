package com.nikita.movies_shmoovies.common.network

import com.nikita.movies_shmoovies.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("/3/movie/upcoming")
    fun getGetUpcoming(@Query("api_key") apiKey: String): Call<ListResponse<Movie>>

    @GET("/3/movie/popular")
    fun getGetPopular(@Query("api_key") apiKey: String): Call<ListResponse<Movie>>

    @GET("/3/movie/top_rated")
    fun getGetTopRated(@Query("api_key") apiKey: String): Call<ListResponse<Movie>>

    @GET("/3/movie/321612")
    fun getGetDetails(@Query("api_key") apiKey: String): Call<ListResponse<Movie>>

}

class MoviesService(api: MoviesApi) : BaseService<MoviesApi>(api) {
    fun getUpcoming() = api.getGetUpcoming(BuildConfig.API_KEY).executeUnsafe()
    fun getTopRated() = api.getGetTopRated(BuildConfig.API_KEY).executeUnsafe()
    fun getPopular() = api.getGetPopular(BuildConfig.API_KEY).executeUnsafe()
    fun getDetails() = api.getGetDetails(BuildConfig.API_KEY).executeUnsafe()


}
