package com.example.movietime.data.model

data class Results(
    val adult: Boolean,
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val release_date: String
)