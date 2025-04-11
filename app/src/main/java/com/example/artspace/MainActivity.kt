package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    var currentArtwork by remember { mutableIntStateOf(1) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xFFFEFBFF))
            .padding(bottom = 50.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtworkWall(currentArtwork = currentArtwork)
        Spacer(modifier = Modifier.height(16.dp))
        ArtworkDescription(currentArtwork = currentArtwork)
        Spacer(modifier = Modifier.height(24.dp))
        NavigationButtons(
            value = currentArtwork,
            onValueChange = { currentArtwork = it }
        )
    }
}

@Composable
fun ArtworkWall(
    modifier: Modifier = Modifier,
    currentArtwork: Int) {
    val artwork = when (currentArtwork) {
        1 -> R.drawable.picture_1
        2 -> R.drawable.picture_2
        3 -> R.drawable.picture_3
        else -> R.drawable.picture_4
    }
    Button(
        modifier = Modifier.padding(16.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFFFEFBFF)),
        shape = MaterialTheme.shapes.large,
        elevation = ButtonDefaults.buttonElevation(8.dp),
        onClick = { /*TODO*/ }
    ) {
        Image(
            painter = painterResource(artwork),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(250.dp)
                .height(400.dp)
                .size(250.dp, 400.dp),
            contentDescription = R.string.app_name.toString(),
        )
    }
}

@Composable
fun ArtworkDescription(
    modifier: Modifier = Modifier,
    currentArtwork: Int,
) {
    val artDescription = when (currentArtwork) {
        1 -> R.string.art_description_1
        2 -> R.string.art_description_2
        3 -> R.string.art_description_3
        else -> R.string.art_description_4
    }
    val artist = when (currentArtwork) {
        1 -> R.string.artist_1
        2 -> R.string.artist_2
        3 -> R.string.artist_3
        else -> R.string.artist_4
    }
    val artDate = when (currentArtwork) {
        1 -> R.string.art_date_1
        2 -> R.string.art_date_2
        3 -> R.string.art_date_3
        else -> R.string.art_date_4
    }
    Surface(
        modifier = Modifier
            .padding(start = 46.dp, end = 46.dp)
            .fillMaxWidth()
            .height(120.dp)
        ,
        color = Color(0xFFECEBF4),
        shape  = MaterialTheme.shapes.small,
    ) {
        Text(
            text = stringResource(artDescription),
            Modifier.padding(16.dp),
            color = Color(0xFF000000),
            fontSize = 24.sp,
            fontWeight = FontWeight.Light
        )
        Row(
            modifier = Modifier.padding(start = 16.dp, top = 80.dp)
        ) {
            Text(
                text = stringResource(artist),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = stringResource(artDate),
                fontWeight = FontWeight.Light
            )
        }

    }
}

@Composable
fun NavigationButtons(
    value: Int,
    onValueChange: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 46.dp, end = 46.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            modifier = Modifier
                .width(140.dp)
                .height(40.dp),
            shape = MaterialTheme.shapes.medium,
            onClick = {
                if (value > 1) {
                    onValueChange(value - 1)
                } else {
                    onValueChange(4)
                }
            })
        {
            Text(text = "Previous")
        }
        Button(
            modifier = Modifier
                .width(140.dp)
                .height(40.dp),
            shape = MaterialTheme.shapes.medium,
            onClick = {
                if (value < 4) {
                    onValueChange(value + 1)
                } else {
                    onValueChange(1)
                }
            }) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}