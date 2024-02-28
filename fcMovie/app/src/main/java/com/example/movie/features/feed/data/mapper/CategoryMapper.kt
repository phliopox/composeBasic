package com.example.movie.features.feed.data.mapper

import com.example.movie.features.common.entity.CategoryEntity
import com.example.movie.features.common.entity.EntityWrapper
import com.example.movie.features.common.entity.MovieDetailEntity
import com.example.movie.features.common.network.model.MovieResponse
import com.example.movie.features.feed.data.FeedConstants
import com.example.movie.library.storage.IStorage
import com.example.movie.features.common.mapper.BaseMapper
import com.example.movie.features.feed.domain.enum.SortOrder
import kr.co.fastcampus.part4plus.movieapp.features.feed.data.mapper.toCategoryList
import kr.co.fastcampus.part4plus.movieapp.features.feed.data.mapper.toMovieDetailEntity
import kr.co.fastcampus.part4plus.movieapp.features.feed.data.mapper.toMovieThumbnailEntity

import javax.inject.Inject

class CategoryMapper @Inject constructor(
    private val storage: IStorage
) : BaseMapper<List<MovieResponse>, List<CategoryEntity>>() {
    override fun getSuccess(
        model: List<MovieResponse>?,
        extra: Any?
    ): EntityWrapper.Success<List<CategoryEntity>> {
        return model?.let {
            EntityWrapper.Success(
                entity = mutableListOf<MovieDetailEntity>()
                    .apply {
                        addAll(model.map { it.toMovieDetailEntity() })
                    }
                    .also {
                        storage.save(FeedConstants.MOVIE_LIST_KEY, it)
                    }
                    .map {
                        it.toMovieThumbnailEntity()
                    }
                    .toCategoryList(if (extra is SortOrder) extra else SortOrder.RATING)
            )
        } ?: EntityWrapper.Success(
            entity = listOf()
        )
    }

    override fun getFailure(error: Throwable): EntityWrapper.Fail<List<CategoryEntity>> {
        return EntityWrapper.Fail(error)
    }
}
