package com.example.zerowaste.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.zerowaste.R

@Composable
fun berhasil(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(Color(0xFFE0FFE0), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                val image: Painter = painterResource(id = R.drawable.mobil)
                Image(
                    painter = image,
                    contentDescription = "Truck Icon",
                    modifier = Modifier
                        .size(200.dp)
                        .clickable {
                            navController.navigate("pickup") {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Sampah",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111111)
            )
            Text(
                text = "Di Jemput",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111111)
            )
        }
    }
}
