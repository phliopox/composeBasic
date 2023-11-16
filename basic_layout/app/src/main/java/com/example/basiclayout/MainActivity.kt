package com.example.basiclayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.basiclayout.ui.theme.BasiclayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasiclayoutTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DialogEx()
                }
            }
        }
    }
}

@Composable
fun DialogEx() {
    var openDialog by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(0) }

    Column {
        Button(onClick = { openDialog = true }) {
            Text("다이얼로그 열기")
        }
        Text("카운터 : $counter")
    }
    if (openDialog) {
        AlertDialog(onDismissRequest = {
            openDialog = false
        }, confirmButton = {
            Button(onClick = {
                counter++
                openDialog = false
            }) {
                Text(text = "더하기")
            }
        }, dismissButton = {
            Button(onClick = { openDialog = false }) {
                Text("취소")
            }
        },
            title = { Text(text = "타이틀") },
            text = { Text("설명문구") })
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BasiclayoutTheme {
        DialogEx()
    }
}