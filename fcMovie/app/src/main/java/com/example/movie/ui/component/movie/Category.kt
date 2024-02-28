package com.example.movie.ui.component.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movie.features.common.entity.CategoryEntity
import com.example.movie.features.feed.presentation.input.IFeedViewModelInput
import com.example.movie.ui.theme.Paddings


@Composable
fun CategoryRow(
    categoryEntity: CategoryEntity,
    input: IFeedViewModelInput
) {
    Column {
        CategoryTitle(titleName = categoryEntity.genre)
        LazyRow(contentPadding = PaddingValues(horizontal = Paddings.large)) {
            itemsIndexed(categoryEntity.movieFeedEntities) { _, item ->
                MovieItem(
                    movie = item,
                    input = input
                )
            }
        }
    }
}

@Composable
fun CategoryTitle(titleName: String) {
    Text("Action", modifier = Modifier.padding(10.dp))
}

