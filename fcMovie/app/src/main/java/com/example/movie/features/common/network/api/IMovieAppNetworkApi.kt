package com.example.movie.features.common.network.api

import com.example.movie.features.common.network.model.MovieResponse
import com.example.movie.library.network.model.ApiResult

interface IMovieAppNetworkApi {
    suspend fun getMovies() : ApiResult<List<MovieResponse>>
}