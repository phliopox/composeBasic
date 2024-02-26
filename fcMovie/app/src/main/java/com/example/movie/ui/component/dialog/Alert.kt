package com.example.movie.ui.component.dialog

import androidx.compose.runtime.Composable
import com.example.movie.ui.component.models.dialog.DialogButton
import com.example.movie.ui.component.models.dialog.DialogContent
import com.example.movie.ui.component.models.dialog.DialogText
import com.example.movie.ui.component.models.dialog.DialogTitle

@Composable
fun DialogPopup.Alert(
    title: String,
    bodyText: String,
    buttons: List<DialogButton>
) {
    BaseDialogPopup(
        dialogTitle = DialogTitle.Header(
            title
        ),
        dialogContent = DialogContent.Large(
            DialogText.Default(
                bodyText
            )
        ),
        buttons = buttons
    )
}
