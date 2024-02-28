package com.example.movie.features.feed.domain.usecase

import com.example.movie.features.common.entity.CategoryEntity
import com.example.movie.features.common.entity.EntityWrapper
import com.example.movie.features.feed.domain.enum.SortOrder

interface IGetFeedCategoryUseCase {
    suspend operator fun invoke(sortOrder: SortOrder?=null) : EntityWrapper<List<CategoryEntity>>
}