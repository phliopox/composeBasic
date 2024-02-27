package com.example.movie.features.common.repository

import com.example.movie.features.common.network.api.IMovieAppNetworkApi
import com.example.movie.features.common.network.model.MovieResponse
import com.example.movie.library.network.model.ApiResponse
import timber.log.Timber
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieNetworkApi: IMovieAppNetworkApi,
  /*  private val storage: IStorage,
    private val categoryMapper: CategoryMapper*/
) :IMovieDataSource {
    override suspend fun getMovieList() {
        val data = movieNetworkApi.getMovies()
        if(data.response is ApiResponse.Success){
            Timber.e(data.response.data.toString())
        }
    }
}