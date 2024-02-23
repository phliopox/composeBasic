package com.example.movie.ui.component.dialog.components.content

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.movie.ui.component.models.dialog.DialogContent
import com.example.movie.ui.component.models.dialog.DialogText

@Composable
fun DialogContentWrapper(dialogContent: DialogContent) {
    Column {
        when(dialogContent){
            is DialogContent.Default ->{
             //   DialogText.Default(dialogContent.dialogText)
            }
            is DialogContent.Large ->{

            }
            is DialogContent.Rating ->{

            }
        }
    }
}