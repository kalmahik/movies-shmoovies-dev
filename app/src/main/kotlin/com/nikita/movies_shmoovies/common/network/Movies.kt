package com.nikita.movies_shmoovies.common.network

data class Movie(val id: String,
                 val title: String,
                 val poster_path: String,
                 val release_date: String,
                 val status: String,
                 val original_language: String,
                 val runtime: Int,
                 val budget: Int,
                 val revenue: Int,
                 val homepage: String)