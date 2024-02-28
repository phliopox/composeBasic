package com.example.movie.features.feed.domain.usecase

import com.example.movie.features.common.entity.CategoryEntity
import com.example.movie.features.common.entity.EntityWrapper
import com.example.movie.features.common.repository.MovieRepository
import com.example.movie.features.feed.domain.enum.SortOrder
import javax.inject.Inject

class GetFeedCategoryUseCase @Inject constructor(private val dataSource: MovieRepository) :
    IGetFeedCategoryUseCase {
    override suspend fun invoke(sortOrder: SortOrder?): EntityWrapper<List<CategoryEntity>> {
        return dataSource.getCategories()
    }

}