package com.example.composebasic

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composebasic.ui.theme.ComposebasicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposebasicTheme {
                // A surface container using the 'background' color from the theme
                /*Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                    ButtonExample(onButtonClicked ={
                        Toast.makeText(this,"toast ~~",Toast.LENGTH_SHORT).show()
                    })
                }*/
                // Greeting()
                /*    ButtonExample(onButtonClicked = {
                        Toast.makeText(this, "toast ~~", Toast.LENGTH_SHORT).show()
                    })*/
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        TopBarEx()
                        CardEx(cardData)
                        CardEx(cardData)
                        CheckBoxEx()
                        TextFieldEx()
                        SlotEx()
                    }
                }
            }
        }

    }

    companion object {
        val cardData = CardData(
            imageUri = "https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part4-chapter3/main/part4-chapter3-10/app/src/main/res/drawable-xhdpi/wall.jpg",
            imageDescription = "엔텔로프 캐년",
            author = "Dalinaum",
            description = "엔텔로프 캐년은 죽기 전에 꼭 봐야할 절경으로 소개되었습니다. "
        )
    }
}

@Composable
fun Greeting() {
    /*Text(
        text = "Hello $name!",
        modifier = modifier
    )*/
    //Color(0xffff9944) 색상 코드 입력 방법
    /*    Text(
            modifier = Modifier.width(300.dp),
            color = Color.Red,
            text = "Hello $name \nHello $name \nHello $name \n",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
            maxLines = 2,
            // letterSpacing = 2.sp,
            textAlign = TextAlign.Center
        )*/


}

//modifier -> constraint의 width 느낌 인가
@Composable
fun ButtonExample(onButtonClicked: () -> Unit) {
    Surface(
        border = BorderStroke(width = 2.dp, color = Color.Black),
        shape = CircleShape,
        modifier = Modifier.padding(20.dp),
        shadowElevation = 10.dp
    ) {
        Button(
            onClick = onButtonClicked,
            //enabled = false,
            //  border = BorderStroke(10.dp,Color.Magenta),
            shape = RoundedCornerShape(10.dp),
            contentPadding = PaddingValues(50.dp),
            // modifier = Modifier.height(100.dp).width(150.dp),
            modifier = Modifier
                .size(200.dp)
                .padding(30.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.DarkGray,  //icon, text color
                containerColor = Color.Cyan // background color
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Send")
        }
    }
}


@Composable
fun CardEx(data: CardData) {
    val placeHolderColor = Color(0x33000000)
    Card(
        modifier = Modifier.padding(4.dp),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = data.imageUri,
                contentDescription = data.imageDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape),
                placeholder = ColorPainter(placeHolderColor)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Column {
                Text(text = data.author)
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = data.description)
            }
        }


    }
}

data class CardData(
    val imageUri: String,
    val imageDescription: String,
    val author: String,
    val description: String
)

@Composable
fun CheckBoxEx() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        /* var checked = remember { mutableStateOf(false) }
         Checkbox(checked = checked.value, onCheckedChange = { checked.value = !checked.value })
         Text(text = "프로그래머입니까?")*/

        // by -> 위임된 속성
        /* var checked by remember { mutableStateOf(false) }
         Checkbox(checked = checked, onCheckedChange = { checked = !checked })
         Text(text = "프로그래머입니까?")*/

        //비구조화
        var (checked, setChecked) = remember { mutableStateOf(false) }
        Checkbox(checked = checked, onCheckedChange = setChecked)
        Text(text = "프로그래머입니까?",
            modifier = Modifier.clickable { setChecked(!checked) })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldEx() {
    var name by remember {
        mutableStateOf("Tom")
    }
    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(value = name,
            label = {
                Text("이름")
            },
            onValueChange = { name = it })
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = "Hello $name")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarEx() {
    Column {
        TopAppBar(title = { Text("") },
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "업 네비게이션"
                    )

                }
            },
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "검색"
                    )

                }
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "설정"
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "계정"
                    )

                }
            }
        )
    }
}

@Composable
fun SlotEx() {
    val checked1 = remember {
        mutableStateOf(false)
    }
    val checked2 = remember {
        mutableStateOf(false)
    }

    val text1 = "텍스트1"
    val text2 = "텍스트2"

    Column {
        CheckBoxSlot(checked = checked1) { Text(text = text1) }
        CheckBoxSlot(checked = checked2) { Text(text = text2) }
    }
}

@Composable
fun CheckBoxSlot(
    checked: MutableState<Boolean>,
    content: @Composable RowScope.() -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            checked.value = !checked.value
        }
    ) {
        Checkbox(checked = checked.value, onCheckedChange = { checked.value = it })
        // Text(text, Modifier.clickable { checked.value = !checked.value })
        content()
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldEx() {
    var checked = remember {
        mutableStateOf(false)
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text("Scaffold App") },
            navigationIcon = {
                IconButton(
                    onClick = { }) {

                }
            }
        )
    }) {
        Surface(Modifier.padding(8.dp)) {
            CheckBoxSlot(checked = checked) {
                Text(text = "컴포즈를 좋아합니다.")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposebasicTheme {
        //  Greeting()
        //CardEx(data = MainActivity.cardData)

    }
}