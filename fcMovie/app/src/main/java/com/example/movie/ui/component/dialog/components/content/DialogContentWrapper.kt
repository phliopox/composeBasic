package com.example.movie.ui.component.dialog.components.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.em
import com.example.movie.ui.component.models.dialog.DialogContent
import com.example.movie.ui.component.models.dialog.DialogText
import com.example.movie.ui.theme.Paddings

data class DialogContentStyle(
    val textStyle: @Composable () -> TextStyle = { MaterialTheme.typography.bodyMedium },
    val contentTopPadding: Dp = Paddings.xlarge,
    val contentBottomPadding: Dp = Paddings.extra
)

val LocalDialogContentStyle = compositionLocalOf { DialogContentStyle() }

@Composable
fun DialogContentWrapper(dialogContent: DialogContent) {
    Column {
        when (dialogContent) {
            is DialogContent.Default -> {
                CompositionLocalProvider(
                    LocalDialogContentStyle provides DialogContentStyle(
                        textStyle = {
                            MaterialTheme.typography.bodySmall.copy(
                                lineHeight = 1.6.em
                            )
                        },
                        contentTopPadding = Paddings.small,
                        contentBottomPadding = Paddings.extra
                    )
                ) {
                    NormalTextContent(dialogContent.dialogText)
                }
                //   DialogText.Default(dialogContent.dialogText)
            }

            is DialogContent.Large -> {
                CompositionLocalProvider(
                    LocalDialogContentStyle provides DialogContentStyle(
                        textStyle = {
                            MaterialTheme.typography.titleMedium.copy(
                                lineHeight = 1.6.em
                            )
                        },
                        contentTopPadding = Paddings.extra,
                        contentBottomPadding = Paddings.extra
                    )
                ) {
                    NormalTextContent(dialogContent.dialogText)
                }
            }

            is DialogContent.Rating -> {
                RatingContent(dialogContent.dialogText)
            }
        }
    }
}

@Composable
fun ColumnScope.NormalTextContent(dialogText: DialogText) {
    Text(
        text = getStringFromDialogText(dialogText),
        modifier = Modifier
            .padding(
                top = LocalDialogContentStyle.current.contentTopPadding,
                bottom = LocalDialogContentStyle.current.contentBottomPadding
            )
            .align(Alignment.CenterHorizontally),
        textAlign = TextAlign.Center,
        style = LocalDialogContentStyle.current.textStyle.invoke()
    )
}

@Composable
fun getStringFromDialogText(dialogText: DialogText): String {
    return dialogText.id?.let {
        stringResource(id = it)
    } ?: dialogText.text ?: ""
}