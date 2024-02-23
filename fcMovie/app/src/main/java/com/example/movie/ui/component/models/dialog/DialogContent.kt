package com.example.movie.ui.component.models.dialog

sealed class DialogContent {

    data class Default(
        val dialogText : DialogText.Default
    ) : DialogContent()

    data class Large (
        val dialogText : DialogText.Default
    ) : DialogContent()

    data class Rating(
        val dialogText : DialogText.Default
    ) : DialogContent()

}
