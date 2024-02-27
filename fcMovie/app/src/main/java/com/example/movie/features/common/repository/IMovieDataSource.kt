package com.example.movie.features.common.repository

interface IMovieDataSource {
    suspend fun getMovieList()
}