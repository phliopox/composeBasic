package com.example.movie.ui.component.dialog

import androidx.compose.runtime.Composable
import com.example.movie.ui.component.models.dialog.DialogButton
import com.example.movie.ui.component.models.dialog.DialogContent
import com.example.movie.ui.component.models.dialog.DialogText
import com.example.movie.ui.component.models.dialog.DialogTitle

object DialogPopup

@Composable
fun DialogPopup.Default(
    title: String,
    bodyText: String,
    buttons: List<DialogButton>
) {
    BaseDialogPopup(
        dialogTitle = DialogTitle.Default(title),
        dialogContent = DialogContent.Default(DialogText.Default(bodyText)),
        buttons = buttons
    )
}