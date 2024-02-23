package com.example.movie.ui.component.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movie.ui.theme.Paddings


@Composable
fun CategoryRow() {
    Column {
        CategoryTitle(titleName = "Action")
        LazyRow(contentPadding = PaddingValues(horizontal = Paddings.large)) {
            //itemIndexed
            item {
                MovieItem()
            }
        }
    }
}

@Composable
fun CategoryTitle(titleName: String) {
    Text("Action", modifier = Modifier.padding(10.dp))
}


@Preview
@Composable
fun CategoryTowPreview() {
    CategoryRow()
}