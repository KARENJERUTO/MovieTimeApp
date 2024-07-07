package com.example.movietime.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movietime.data.model.MovieListResponse
import com.example.movietime.data.repository.MovieRepository
import com.example.movietime.util.Resource
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    val movies = MutableLiveData<Resource<MovieListResponse>>()

    fun getPopularMovies(apiKey: String) {
        viewModelScope.launch {
            movies.postValue(Resource.loading(null))
            val response = movieRepository.getPopularMovies(apiKey)
            movies.postValue(response)
        }
    }
}
