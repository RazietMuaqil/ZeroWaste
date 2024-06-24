package com.example.zerowasteproject.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import com.example.app.R

@Composable
fun profile(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {
        Text(
            text = "Account",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(10.dp))
        UserProfile()
        Spacer(modifier = Modifier.height(16.dp))
        NavigationItem(
            icon = painterResource(id = R.drawable.info),
            text = "Panduan Aplikasi",
        )
        Spacer(modifier = Modifier.height(1.dp))
        NavigationItem(
            icon = painterResource(id = R.drawable.syarat),
            text = "Syarat dan Ketentuan"
        )
        Spacer(modifier = Modifier.height(1.dp))
        NavigationItem(
            icon = painterResource(id = R.drawable.langganan),
            text = "Langganan"
        )
        Spacer(modifier = Modifier.height(1.dp))
        NavigationItem(
            icon = painterResource(id = R.drawable.bantuan),
            text = "Bantuan"
        )
        Spacer(modifier = Modifier.height(1.dp))
        NavigationItem(
            icon = painterResource(id = R.drawable.keluar),
            text = "Keluar"
        )
    }
}

@Composable
fun UserProfile() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF388E3C), shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(70.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.account),
                    contentDescription = null,
                    tint = Color(0xFFF5F5F5),
                    modifier = Modifier.size(120.dp)
                )

            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "John David",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { /* TODO: Edit profile action */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(text = "Edit Profil", color = Color(0xFF388E3C))
                }
            }
        }
    }
}



@Composable
fun NavigationItem(icon: Painter, text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .border(1.dp, Color(0xFF388E3C), RoundedCornerShape(8.dp))
            .clickable { /* TODO: Navigation action */ }
            .padding(20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = icon,
                contentDescription = null,
                tint = Color(0xD8222222),
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = text,
                fontSize = 16.sp,
                color = Color.Black,
            )
        }
    }
}