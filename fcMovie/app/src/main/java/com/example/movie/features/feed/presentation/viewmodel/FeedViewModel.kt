package com.example.movie.features.feed.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.features.common.repository.IMovieDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(private val repository: IMovieDataSource) : ViewModel() {

    fun getMovie() {
        viewModelScope.launch {
            repository.getMovieList()
        }
    }
}