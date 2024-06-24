package com.example.zerowaste.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun splash(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate("Login") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF388E3C)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "ZeroWaste",
            color = Color.White,
            fontSize = 37.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .wrapContentSize()
                .offset(y = (-20).dp) // Adjust the y value to move the text up
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A5A1C)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "ZeroWaste",
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier
                .wrapContentSize()
                .offset(y = (-20).dp) // Adjust the y value to move the text up in the preview as well
        )
    }
}
