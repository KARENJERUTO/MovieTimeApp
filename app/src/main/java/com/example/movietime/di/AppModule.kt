package com.example.movietime.di

import com.example.movietime.data.api.RetrofitInstance
import com.example.movietime.data.repository.MovieRepository
import com.example.movietime.ui.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { RetrofitInstance.api }
    single { MovieRepository(get()) }
    viewModel { MovieViewModel(get()) }

}
