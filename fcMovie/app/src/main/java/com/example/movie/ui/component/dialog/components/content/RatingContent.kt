package com.example.movie.ui.component.dialog.components.content

import android.content.res.ColorStateList
import android.text.Layout
import android.widget.RatingBar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.movie.ui.component.models.dialog.DialogText
import com.example.movie.ui.theme.Paddings
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.viewinterop.AndroidView
import com.example.movie.ui.theme.colorscheme
import com.example.movie.ui.util.getAnnotatedText

@Composable
fun RatingContent(content: DialogText.Rating) {
    Column(
        modifier = Modifier.padding(top = Paddings.large, bottom = Paddings.xlarge)
    ) {
        RatingTable(
            rating = content.rating,
            movieTitle = content.text ?: ""
        )
    }
}

@Composable
fun ColumnScope.RatingTable(rating: Float, movieTitle: String) {
    Column(
        modifier =
        Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = getAnnotatedText(movieTitle),
            style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorscheme.secondary),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(Paddings.large))
        StarRatingBar(score = rating)
    }
}

@Composable
fun ColumnScope.StarRatingBar(score: Float) {
    Card(
        modifier = Modifier
            .padding(Paddings.medium)
            .wrapContentSize()
            .align(Alignment.CenterHorizontally),
        elevation = CardDefaults.cardElevation(Paddings.none)
    ) {

        Box(contentAlignment = Alignment.Center) {
            val foregroundColor = MaterialTheme.colorscheme.primary.toArgb()
            val starBackgroundColor = MaterialTheme.colorscheme.primaryVariant.copy(
                alpha = 0.2f
            ).toArgb()
            val ratingBackgroundColor = MaterialTheme.colorScheme.primaryContainer.copy(
                alpha = 0.05f
            ).toArgb()

            AndroidView(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.Center),
                factory = {
                    RatingBar(
                        it
                    ).apply {
                        max = 10
                        stepSize = 25f
                        rating = score
                        numStars = 5
                        progressTintList = ColorStateList.valueOf(foregroundColor)
                        progressBackgroundTintList = ColorStateList.valueOf(starBackgroundColor)
                        setBackgroundColor(ratingBackgroundColor)
                    }
                }
            )
        }
    }
}