package com.example.movie.ui.component.dialog.components.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.movie.ui.component.buttons.PrimaryButton
import com.example.movie.ui.component.buttons.SecondaryBorderlessButton
import com.example.movie.ui.component.buttons.SecondaryButton
import com.example.movie.ui.component.buttons.UnderlinedTextButton
import com.example.movie.ui.component.models.dialog.DialogButton
import com.example.movie.ui.theme.Paddings

@Composable
fun DialogButtonsColumn(dialogButtons: List<DialogButton>?) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        dialogButtons?.forEachIndexed { index, dialogButton ->
            if (index > 0) {
                Spacer(modifier = Modifier.height(Paddings.large))
            }
            when (dialogButton) {
                is DialogButton.Primary -> {
                    PrimaryButton(
                        text = dialogButton.title,
                        leadingIconData = dialogButton.leadingIconData
                    ) {
                        dialogButton.action?.invoke()
                    }
                }

                is DialogButton.Secondary -> {
                    SecondaryButton(
                        text = dialogButton.title
                    ) {
                        dialogButton.action?.invoke()
                    }
                }

                is DialogButton.SecondaryBorderless -> {
                    SecondaryBorderlessButton(
                        text = dialogButton.title
                    ) {
                        dialogButton.action?.invoke()
                    }
                }

                is DialogButton.UnderlinedText -> {
                    UnderlinedTextButton(
                        text = dialogButton.title
                    ) {
                        dialogButton.action?.invoke()
                    }
                }
            }
        }
    }
}