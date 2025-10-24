package com.example.moviesearchapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesearchapp.data.model.Movie
import com.example.moviesearchapp.data.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(
    private val repository: MovieRepository = MovieRepository()
) : ViewModel() {
    
    var movies by mutableStateOf<List<Movie>>(emptyList())
        private set
    
    var query by mutableStateOf("")
        private set
    
    var isLoading by mutableStateOf(false)
        private set
    
    var errorMessage by mutableStateOf<String?>(null)
        private set
    
    fun onQueryChange(newQuery: String) {
        query = newQuery
    }
    
    fun searchMovies() {
        if (query.isBlank()) {
            errorMessage = "Please enter a movie title"
            return
        }
        
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            
            val response = repository.searchMovies(query)
            
            isLoading = false
            
            if (response.Response == "True") {
                movies = response.Search ?: emptyList()
                if (movies.isEmpty()) {
                    errorMessage = "No movies found"
                }
            } else {
                errorMessage = response.Error ?: "Failed to fetch movies"
                movies = emptyList()
            }
        }
    }
    
    fun clearError() {
        errorMessage = null
    }
}