package com.example.moviesearchapp.data.model

data class Movie(
    val Title: String,
    val Year: String,
    val imdbID: String,
    val Poster: String,
    val Type: String? = null
)