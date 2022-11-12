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
    println("userChoose = $userChoose")

    when (userChoose) {
        0 -> ArtSpaceDisplay(
            image = painterResource(id = R.drawable.done),
            title = stringResource(id = R.string.title),
            fullName = stringResource(id = R.string.fullName),
            year = stringResource(id = R.string.year)
        )
        1 -> ArtSpaceDisplay(
            image = painterResource(id = R.drawable.android_logo),
            title = stringResource(id = R.string.title),
            fullName = stringResource(id = R.string.title),
            year = stringResource(id = R.string.title)
        )
        2 -> ArtSpaceDisplay(
            image = painterResource(id = R.drawable.androidparty),
            title = stringResource(id = R.string.fullName),
            fullName = stringResource(id = R.string.fullName),
            year = stringResource(id = R.string.fullName)
        )
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row(horizontalArrangement = Arrangement.Center) {
            if (userChoose > 0)
                Button(
                    onClick = { userChoose-- },
                    Modifier
                        .padding(15.dp)
                        .weight(1f)

                ) {
                    Text(text = stringResource(R.string.previous))

                }
            if (userChoose < 2) {
                Button(
                    onClick = { userChoose++ },
                    Modifier
                        .padding(15.dp)
                        .weight(1f)
                ) {
                    Text(text = stringResource(R.string.next))
                }
            }
        }
    }
}


@Composable
fun ArtSpaceDisplay(image: Painter, title: String, fullName: String, year: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.background(Color.White)
    ) {
        ArtworkWall(image = image)
        ArtworkDescription(
            title = title,
            fullName = fullName,
            year = year
        )
    }

}

@Composable
fun ArtworkWall(image: Painter) {
    Image(
        painter = image,
        contentDescription = image.toString(),
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .padding(15.dp)
            .border(2.dp, color = Color.Black)
    )
}

@Composable
fun ArtworkDescription(title: String, fullName: String, year: String) {
    Column(
        modifier = Modifier.border(2.dp, color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = title, fontSize = 36.sp,
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



