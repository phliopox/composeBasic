package com.example.movie.ui.component.button

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.movie.ui.theme.MovieTheme
import com.example.movie.ui.theme.Paddings
import com.example.movie.ui.theme.colorscheme

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    @StringRes id: Int? = null,
    text: String = "",
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorscheme.onPrimary,
            containerColor = MaterialTheme.colorscheme.primary,
            disabledContainerColor = MaterialTheme.colorscheme.disabledSecondary,
            disabledContentColor = MaterialTheme.colorscheme.background
        ),
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = id?.let { stringResource(id = id) } ?: text,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(Paddings.small))
        }
    }
}

@Preview
@Composable
fun PreviewButton() {
    MovieTheme {
        PrimaryButton(text = "SUBMIT") {
        }
    }
}