package com.example.movie.ui.component.dialog.components.title


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.example.movie.ui.component.models.dialog.DialogTitle
import com.example.movie.ui.theme.Paddings
import com.example.movie.ui.theme.colorscheme
import com.example.movie.ui.theme.h5Title

data class DialogTextStyle(
    val textStyle: @Composable () -> TextStyle = { MaterialTheme.typography.bodyLarge },
    //val textModifier: Modifier = Modifier.fillMaxWidth(),
    val headerBackground: @Composable () -> Color = { MaterialTheme.colorscheme.background },
    val padding: Pair<Dp, Dp>? = Pair(Paddings.large, Paddings.large)
)

val LocalDialogTitleStyle = compositionLocalOf { DialogTextStyle() }

@Composable
fun DialogTitleWrapper(dialogTitle: DialogTitle) {
    when (dialogTitle) {
        is DialogTitle.Default -> {
            CompositionLocalProvider(
                LocalDialogTitleStyle provides DialogTextStyle(
                    textStyle = {
                        MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorscheme.secondary)
                    },
                    headerBackground = { MaterialTheme.colorscheme.background },
                    padding = Pair(Paddings.large, Paddings.extra)

                )
            ) {
                DialogTitleColumn(dialogTitle.text)
            }
        }

        is DialogTitle.Header -> {

            CompositionLocalProvider(
                LocalDialogTitleStyle provides DialogTextStyle(
                    textStyle = {
                        MaterialTheme.typography.headlineLarge.copy(
                            color = MaterialTheme.colorScheme.onPrimary)
                    },
                    headerBackground = { MaterialTheme.colorscheme.primary },
                    padding = Pair(Paddings.large, Paddings.large)
                )
            ) {
                DialogTitleColumn(dialogTitle.text)
            }
        }

        is DialogTitle.Large -> {

            CompositionLocalProvider(
                LocalDialogTitleStyle provides DialogTextStyle(
                    textStyle = {
                        MaterialTheme.typography.h5Title.copy(
                            color = MaterialTheme.colorscheme.secondary)
                    },
                    headerBackground = { MaterialTheme.colorscheme.background },
                    padding = Pair(Paddings.xlarge, Paddings.xlarge)
                )
            ) {
                DialogTitleColumn(dialogTitle.text)
            }
        }
    }
}

@Composable
fun DialogTitleColumn(title: String) {
    val horizontalP = LocalDialogTitleStyle.current.padding!!.first
    val verticalP = LocalDialogTitleStyle.current.padding!!.second
    Column(
        modifier =
        Modifier
            .fillMaxWidth()
            .background(LocalDialogTitleStyle.current.headerBackground.invoke())
            .padding(horizontalP, verticalP),

        verticalArrangement = Arrangement.Center

    ) {
        Text(
            text = title,
            style = LocalDialogTitleStyle.current.textStyle.invoke(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )
    }
}