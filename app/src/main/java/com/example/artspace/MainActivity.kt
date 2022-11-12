package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}


@Composable
@Preview
fun ArtSpaceApp() {
    var userChoose by remember {
        mutableStateOf(1)
    }

    when (userChoose) {
        0 -> ArtSpaceDisplay(
            image = painterResource(id = R.drawable.one),
            title = stringResource(id = R.string.title),
            fullName = stringResource(id = R.string.fullName),
            year = stringResource(id = R.string.year),
            { userChoose++ },
            { userChoose-- }, userChoose

        )
        1 -> ArtSpaceDisplay(
            image = painterResource(id = R.drawable.two),
            title = stringResource(id = R.string.title),
            fullName = stringResource(id = R.string.title),
            year = stringResource(id = R.string.title),
            { userChoose++ },
            { userChoose-- }, userChoose
        )
        2 -> ArtSpaceDisplay(
            image = painterResource(id = R.drawable.three),
            title = stringResource(id = R.string.fullName),
            fullName = stringResource(id = R.string.fullName),
            year = stringResource(id = R.string.fullName),
            { userChoose++ },
            { userChoose-- }, userChoose
        )
        3 -> ArtSpaceDisplay(
            image = painterResource(id = R.drawable.four),
            title = stringResource(id = R.string.fullName),
            fullName = stringResource(id = R.string.fullName),
            year = stringResource(id = R.string.fullName),
            { userChoose++ },
            { userChoose-- }, userChoose
        )
    }

}


@Composable
fun ArtSpaceDisplay(
    image: Painter, title: String, fullName: String, year: String, onClickNext: () -> Unit,
    onClickPrev: () -> Unit, cur: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.White)
            .border(2.dp, Color.LightGray),
        verticalArrangement = Arrangement.Top
    ) {
        ArtworkWall(image = image)
        ArtworkDescription(
            title = title,
            fullName = fullName,
            year = year
        )
        ArtworkNavigation(onClickNext, onClickPrev, cur)
    }

}

@Composable
fun ArtworkWall(image: Painter) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(20.dp, 5.dp)
            .border(2.dp, Color.Gray),
    )
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth(1f)
                .background(Color.White),
            verticalArrangement = Arrangement.Top
        )
        {
            Image(
                painter = image,
                contentDescription = image.toString(),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(30.dp)

            )
        }
    }
}

@Composable
fun ArtworkDescription(
    title: String,
    fullName: String,
    year: String
) {
    Card(
        elevation = 4.dp,
        modifier = Modifier.padding(20.dp, 10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Text(
                text = title, fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Row(horizontalArrangement = Arrangement.Center) {
                Text(
                    text = fullName,
                    modifier = Modifier.padding(5.dp),
                    fontSize = 24.sp,
                )
                Text(
                    text = "($year)",
                    modifier = Modifier.padding(5.dp),
                    fontSize = 24.sp,
                )
            }
        }
    }

}

@Composable
fun ArtworkNavigation(
    onClickNext: () -> Unit,
    onClickPrev: () -> Unit,
    cur: Int
) {

    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        if (cur > 0) {
            Button(
                onClick = onClickPrev,
                Modifier
                    .padding(20.dp, 0.dp)
                    .weight(1f)
            ) {
                Text(text = stringResource(R.string.previous))
            }
        }
        if (cur < 3) {
            Button(
                onClick = onClickNext,
                Modifier
                    .padding(20.dp, 0.dp)
                    .weight(1f)
            ) {
                Text(text = stringResource(R.string.next))
            }
        }
    }
}
