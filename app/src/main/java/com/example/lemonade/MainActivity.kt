package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    var screenNumber by remember {
        mutableStateOf(1)
    }
    val currentScreenContentDescription: Int
    val image = when (screenNumber) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val currentScreenText = when (screenNumber) {
        1 -> {
            currentScreenContentDescription = R.string.tree_content_description
            R.string.tree
        }

        2 -> {
            currentScreenContentDescription = R.string.lemon_content_description
            R.string.lemon
        }

        3 -> {
            currentScreenContentDescription = R.string.lemonade_content_description
            R.string.lemonade
        }

        else -> {
            currentScreenContentDescription = R.string.empty_glass_content_description
            R.string.empty_glass
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        var currentClicksNumber = 0
        val lemonClicksAmount = (2..4).random()
        Button(
            onClick = {
                if (screenNumber == 2) {
                    currentClicksNumber++
                    if (currentClicksNumber == lemonClicksAmount) screenNumber++
                } else if (screenNumber == 4) {
                    screenNumber = 1
                } else {
                    screenNumber++
                }
            },
            shape = RoundedCornerShape(10),
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = stringResource(id = currentScreenContentDescription)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = currentScreenText),
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}