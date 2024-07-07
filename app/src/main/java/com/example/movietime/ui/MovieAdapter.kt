package com.example.movietime.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movietime.data.model.Results
import com.example.movietime.databinding.ItemMovieBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movies: List<Results> = listOf()

    fun submitList(movies: List<Results>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    inner class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Results) {
            binding.movieTitle.text = result.title // Ensure this matches your data class property
            binding.movieOverview.text = result.overview // Ensure this matches your data class property
            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w500${result.poster_path}") // Ensure this matches your data class property
                .into(binding.moviePoster)
        }
    }
}
