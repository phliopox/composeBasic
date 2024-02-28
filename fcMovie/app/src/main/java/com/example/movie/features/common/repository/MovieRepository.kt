package com.example.movie.features.common.repository

import com.example.movie.features.common.entity.CategoryEntity
import com.example.movie.features.common.entity.EntityWrapper
import com.example.movie.features.common.entity.MovieDetailEntity
import com.example.movie.features.common.network.api.IMovieAppNetworkApi
import com.example.movie.features.feed.data.FeedConstants
import com.example.movie.library.storage.IStorage
import com.example.movie.features.feed.data.mapper.CategoryMapper
import com.example.movie.features.feed.domain.enum.SortOrder
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieNetworkApi: IMovieAppNetworkApi,
    private val storage: IStorage,
    private val categoryMapper: CategoryMapper
) :IMovieDataSource {

    override suspend fun getCategories(sortOrder: SortOrder?): EntityWrapper<List<CategoryEntity>> {
        return categoryMapper.mapFromResult(
            result = movieNetworkApi.getMovies(),
            extra = sortOrder
        )
    }

    override suspend fun getMovieDetail(movieName: String): MovieDetailEntity {
        return storage
            .get<List<MovieDetailEntity>>(FeedConstants.MOVIE_LIST_KEY)
            ?.single { it.title == movieName }
            ?: MovieDetailEntity()
    }


}