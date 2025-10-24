package com.example.moviesearchapp.data.repository

import com.example.moviesearchapp.data.model.MovieSearchResponse
import com.example.moviesearchapp.data.remote.RetrofitInstance

class MovieRepository {
    suspend fun searchMovies(query: String): MovieSearchResponse {
        return try {
            RetrofitInstance.api.searchMovies(searchQuery = query)
        } catch (e: Exception) {
            MovieSearchResponse(
                Search = null,
                totalResults = null,
                Response = "False",
                Error = e.message ?: "Unknown error occurred"
            )
        }
    }
}