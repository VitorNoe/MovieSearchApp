package com.example.moviesearchapp.data.remote

import com.example.moviesearchapp.data.model.MovieSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/")
    suspend fun searchMovies(
        @Query("apikey") apiKey: String = "2ae73ec0", // Get from omdbapi.com
        @Query("s") searchQuery: String
    ): MovieSearchResponse
}