package com.example.movie.ui.component.dialog

import androidx.compose.runtime.Composable
import com.example.movie.ui.component.models.dialog.DialogButton
import com.example.movie.ui.component.models.dialog.DialogContent
import com.example.movie.ui.component.models.dialog.DialogText
import com.example.movie.ui.component.models.dialog.DialogTitle

@Composable
fun DialogPopup.Rating(
    movieName: String,
    rating: Float,
    buttons: List<DialogButton>
) {
    BaseDialogPopup(
        dialogTitle = DialogTitle.Large("Rate your Score!"),
        dialogContent = DialogContent.Rating(
            DialogText.Rating(
                text = movieName,
                rating = rating
            )
        ),
        buttons = buttons
    )
}
