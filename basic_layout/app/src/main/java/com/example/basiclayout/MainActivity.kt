package com.example.basiclayout

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.basiclayout.ui.theme.BasiclayoutTheme
import kotlinx.coroutines.launch

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
                    //DialogEx()
                    //CustomDialog()
                    //DropDownMenuEx()
                    snackBarEx()
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

@Composable
fun CustomDialog() {
    var openDialog by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(0) }

    Column {
        Button(onClick = { openDialog = true }) {
            Text("다이얼로그 열기")
        }
        Text("카운터 : $counter")
    }

    if (openDialog) {
        Dialog(onDismissRequest = {
            openDialog = false
        }) {
            Surface {
                Column(Modifier.padding(8.dp)) {
                    Text("카운터")
                    Text("버튼을 눌러 카운트 버튼을 조절해보세요")
                    Row(Modifier.align(Alignment.CenterHorizontally)) {
                        Button(onClick = {
                            openDialog = false
                        }) {
                            Text(text = "취소")
                        }
                        Button(onClick = {
                            counter--
                            openDialog = false
                        }) {
                            Text(text = " -1")
                        }
                        Button(onClick = {
                            counter++
                            openDialog = false
                        }) {
                            Text(text = " +1")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DropDownMenuEx() {
    var expandDropDownMenu by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(0) }
    Column {
        Text("카운터 : $counter")

        Box {
            Button(onClick = { expandDropDownMenu = true }) {
                Text(text = "드롭다운 메뉴 열기")
            }
            DropdownMenu(
                expanded = expandDropDownMenu,
                onDismissRequest = {
                    expandDropDownMenu = false

                }
            ) {
                DropdownMenuItem(text = { Text("+1") },
                    onClick = {
                        counter++
                        expandDropDownMenu = false
                    })

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun snackBarEx() {
    var counter by remember { mutableStateOf(0) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    //coroutineScope  onclick 이벤트로 사용할 시 컴포즈 내부에서 사용되는 게 아니라서 상관없지만,
    //컴포즈에서 사용할 경우 (부작용 처리?) LaunchedEffect 를 사용해야한다 .

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }) {
        Column(Modifier.padding(it)) {
            Button(onClick = {
                counter++
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "카운터는 $counter 입니다.",
                        actionLabel = "닫기",
                        duration = SnackbarDuration.Short
                    )
                }
            }) {
                Text("더하기")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BasiclayoutTheme {
        //DialogEx()
        //  CustomDialog()
        //DropDownMenuEx()
        snackBarEx()
    }
}