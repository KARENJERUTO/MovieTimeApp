package com.example.movietime.data.repository

import com.example.movietime.data.api.MovieApi
import com.example.movietime.data.model.MovieListResponse
import com.example.movietime.util.Resource

class MovieRepository(private val movieApi: MovieApi) {
    suspend fun getPopularMovies(apiKey: String): Resource<MovieListResponse> {
        return try {
            val response = movieApi.getPopularMovies(apiKey)
            Resource.success(response)
        } catch (e: Exception) {
            Resource.error(null, e.localizedMessage ?: "An error occurred")
        }
    }
}
