package com.example.constraint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.example.constraint.ui.theme.ConstraintTheme
import kotlinx.coroutines.NonDisposableHandle.parent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConstraintTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConstraintEX()
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

data class CardData(
    val imageUri: String,
    val imageDescription: String,
    val author: String,
    val description: String
)

@Composable
fun ConstraintLayoutEx() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        // 단계 2: createRefs()를 이용해서 아래 박스들의 레퍼런스를 가져옵시다.
        // createRefs는 여러개의 레퍼런스를 리턴하니 destruction으로 분해합시다.
        // red, magenta, green, yellow 박스가 있습니다.

        Box(
            // 단계 3: constraintsAs 모디파이어를 추가하고 레퍼런스를 전달합시다.
            // 후행 람다로 top, start, end, bottom 앵커를 지정하고
            // linkTo 호출합니다.
            // 인자로는 parent의 앵커(top, start, end, bottom)을
            // 전달해봅시다.

            // 단계 4: linkTo의 키워드 인자 margin을 추가합시다.
            modifier = Modifier
                .size(40.dp)
                .background(Color.Red)
        )
        Box(
            // 단계 5: 마젠타 박스를 parent의 start와 end에 연결합시다.
            modifier = Modifier
                .size(40.dp)
                .background(Color.Magenta)
        )
        Box(
            // 단계 6: 그린 박스를 linkTo를 이용해서 정 가운데로 연결해봅시다.

            // 단계 7: 앵커 메서드 linkTo 대신에 centerTo 함수를 사용해봅시다.
            modifier = Modifier
                .size(40.dp)
                .background(Color.Green)
        )

        Box(
            // 단계 8: 옐로 박스를 마젠타 박스 오른쪽 대각선 아래에 위치해봅시다.
            // linkTo를 쓰고 인자로 parent 대신 그린 박스의 레퍼런스를 사용합시다.
            modifier = Modifier
                .size(40.dp)
                .background(Color.Yellow)
        )
    }
}

@Composable
fun ConstraintEX() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (redBox, magentaBox, greenBox, yellowBox) = createRefs()
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Red)
                .constrainAs(redBox) {
                    bottom.linkTo(parent.bottom, 8.dp)
                    end.linkTo(parent.end, 4.dp)
                }
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Magenta)
                .constrainAs(magentaBox) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Green)
                .constrainAs(greenBox) {
                    centerTo(parent)
                }
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Yellow)
                .constrainAs(yellowBox) {
                }
        )
    }
}

@Composable
fun ConstraintSetEx() {
    val constraintSet = ConstraintSet {
        val redBox = createRefFor("redBox")
        val magentaBox = createRefFor("magentaBox")
        val greenBox = createRefFor("greenBox")
        val yellowBox = createRefFor("yellowBox")

        constrain(redBox) {
            bottom.linkTo(parent.bottom, 8.dp)
            end.linkTo(parent.end, 4.dp)
        }
        constrain(magentaBox) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(greenBox) {
            centerTo(parent)

        }
        constrain(yellowBox) {
        }


    }
    ConstraintLayout(
        constraintSet,
        Modifier.fillMaxSize()
    ) {
        //  val (redBox, magentaBox, greenBox, yellowBox) = createRefs()
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Red)
                .layoutId("redBox")
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Magenta)
                .layoutId("magentaBox")
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Green)
                .layoutId("greenBox")
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Yellow)

        )
    }
}


@Composable
fun ConstraintChain() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (redBox, magentaBox, yellowBox, text) = createRefs()
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Red)
                .constrainAs(redBox) {
                    top.linkTo(parent.top, 18.dp)
                }
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Magenta)
                .constrainAs(magentaBox) {
                    top.linkTo(parent.top, 30.dp)
                }
        )

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Yellow)
                .constrainAs(yellowBox) {
                    top.linkTo(parent.top, 18.dp)
                }
        )
        // createVerticalChain(redBox,yellowBox,magentaBox)
        createHorizontalChain(redBox, yellowBox, magentaBox, chainStyle = ChainStyle.Packed)
        val barrier = createBottomBarrier(redBox, yellowBox, magentaBox)
        Text(
            text = "나라말슴이 듕귁에 달아 ",
            Modifier.constrainAs(text) {
                top.linkTo(barrier)
            })
    }

}

@Composable
fun ConstraintCard(cardData: CardData) {
    Card(
        Modifier
            .padding(4.dp), elevation = CardDefaults.cardElevation(8.dp)
    ) {
        ConstraintLayout(Modifier.fillMaxWidth()) {
            val (image, name, description) = createRefs()
            AsyncImage(
                model = cardData.imageUri,
                contentDescription = cardData.imageDescription,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .constrainAs(image) {
                        centerVerticallyTo(parent)
                        start.linkTo(parent.start, 8.dp)
                        /*
                        top.linkTo(name.top)
                        bottom.linkTo(description.bottom)*/
                    },
                placeholder = ColorPainter(Color.Black),
                contentScale = ContentScale.Crop
            )

            Text(cardData.author, Modifier.constrainAs(name) {
               /* start.linkTo(image.end, 8.dp)
                top.linkTo(parent.top)*/
                linkTo(image.end,parent.end, 8.dp,8.dp)
                width = Dimension.fillToConstraints
            })
            Text(cardData.description, Modifier.constrainAs(description) {
                linkTo(image.end,parent.end,8.dp,8.dp)
                width = Dimension.fillToConstraints
                /*top.linkTo(name.bottom, 4.dp)
                start.linkTo(name.start)*/
            })
            val chain = createVerticalChain(name, description, chainStyle = ChainStyle.Packed)
            constrain(chain){
                top.linkTo(parent.top , 8.dp)
                bottom.linkTo(parent.bottom,8.dp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ConstraintTheme {
        //  ConstraintEX()
        // ConstraintSetEx()
        //ConstraintChain()
        ConstraintCard(cardData = MainActivity.cardData)
    }
}