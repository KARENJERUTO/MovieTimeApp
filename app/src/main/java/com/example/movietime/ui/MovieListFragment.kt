package com.example.movietime.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.movietime.R
import com.example.movietime.databinding.FragmentMovieListBinding
import com.example.movietime.util.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.google.android.material.snackbar.Snackbar

class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private val movieViewModel: MovieViewModel by viewModel() // Inject ViewModel

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter()
        binding.recyclerView.adapter = movieAdapter

        movieViewModel.movies.observe(viewLifecycleOwner, Observer { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    resource.data?.let { movieListResponse ->
                        if (movieListResponse.results.isNotEmpty()) {
                            movieAdapter.submitList(movieListResponse.results)
                            binding.recyclerView.visibility = View.VISIBLE

                        } else {
                            binding.recyclerView.visibility = View.GONE
                        }
                    }
                }
                Resource.Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE

                    Snackbar.make(view, resource.message ?: getString(R.string.an_error_occurred), Snackbar.LENGTH_LONG).show()
                }
                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
            }
        })

        movieViewModel.getPopularMovies("b5c3b9807101bcd9c04917c917742d2d")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
