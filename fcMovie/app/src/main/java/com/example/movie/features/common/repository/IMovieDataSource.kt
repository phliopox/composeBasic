package com.example.movie.features.common.repository

import com.example.movie.features.common.entity.CategoryEntity
import com.example.movie.features.common.entity.EntityWrapper
import com.example.movie.features.common.entity.MovieDetailEntity
import com.example.movie.features.feed.domain.enum.SortOrder

interface IMovieDataSource {
    suspend fun getCategories(sortOrder : SortOrder? =null) : EntityWrapper<List<CategoryEntity>>
    suspend fun getMovieDetail(movieName:String):MovieDetailEntity
}