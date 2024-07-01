package com.example.animatevisibility

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animatevisibility.ui.theme.AnimateVisibilityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimateVisibilityTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun CustomButton(
    text: String,
    targetState: Boolean,
    onClick: (Boolean) -> Unit,
    bgColor: Color = Color.Blue
) {

    Button(
        onClick = { onClick(!targetState) },
        colors = ButtonDefaults.buttonColors(
            containerColor = bgColor,
            contentColor = Color.White)
    ) {
        Text(text = text)
    }
}

@Composable
fun MainScreen() {
    var boxVisible by remember { mutableStateOf(true) }
    val onClick = { newState: Boolean ->
        boxVisible = newState
    }
    Column(
        Modifier
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CustomButton(
                text = "Show",
                targetState = false,
                onClick = onClick,
                bgColor = colorResource(id = R.color.steel_blue)

            )
            CustomButton(
                text = "Hide",
                targetState = true,
                onClick = onClick,
                bgColor = colorResource(id = R.color.light_steel_blue)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        if (boxVisible) {
            Box(
                modifier = Modifier
                    .size(height = 200.dp, width = 200.dp)
                    .background(colorResource(id = R.color.steel_blue))
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Box Visible",
                    style = MaterialTheme.typography.displayLarge,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    AnimateVisibilityTheme {
        MainScreen()
    }
}