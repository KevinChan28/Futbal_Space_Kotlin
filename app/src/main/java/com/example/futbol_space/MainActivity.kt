package com.example.futbol_space

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.futbol_space.ui.theme.Futbol_SpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Futbol_SpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FootballerGallery(footballers)
                }
            }
        }
    }
}

data class Footballer(val imageResource: Int, val name: String, val birthPlace: String)
val footballers = listOf(
    Footballer(R.drawable.messi2, "L.Messi", "Argentina"),
    Footballer(R.drawable.cr7, "C.Ronaldo", "Portugal"),
    Footballer(R.drawable.haaland, "Haaland", "Noruega"),
    Footballer(R.drawable.killy, "Mbappe", "Francia"),
    Footballer(R.drawable.kevin, "K. De Bruyne", "Belgica")
)

@Composable
fun FootballerCard(footballer: Footballer) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = footballer.imageResource), contentDescription = null)
        Text(text = footballer.name)
        Text(text = footballer.birthPlace)
    }
}

@Composable
fun FootballerGallery(footballers: List<Footballer>) {
    var currentIndex by remember { mutableStateOf(0) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        FootballerCard(footballer = footballers[currentIndex])

        Row {
            Button(onClick = { currentIndex = (currentIndex - 1 + footballers.size) % footballers.size }) {
                Text("Anterior")
            }

            Button(onClick = { currentIndex = (currentIndex + 1) % footballers.size }) {
                Text("Siguiente")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun  FootballerGallery() {
    Futbol_SpaceTheme {
        FootballerGallery(footballers)
    }
}