package com.example.zerowaste.edukasi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.zerowaste.R
import com.example.zerowaste.poin.Item

@Composable
fun reuce(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Spacer(modifier = Modifier.width(48.dp))
                            Text(
                                text = "Prinsip Reuce",
                                fontSize = 20.sp,
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.width(48.dp))
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("edukasi") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Back"
                        )
                    }
                },
                backgroundColor = Color(0xFF4CAF50),
                contentColor = Color.White
            )
        },
        content = { PaddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(PaddingValues)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                VideoReuce()
                SectionReuce(navController)
            }
        }
    )
}

@Composable
fun VideoReuce() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Video",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF388E3C),
                modifier = Modifier.padding(5.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Image(
                painter = painterResource(R.drawable.vidreuce),
                contentDescription = "Video Thumbnail Reuce",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
    }
}

@Composable
fun SectionReuce(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(start = 4.dp, top = 8.dp)
    ) {
        Text(
            text = "Materi Edukasi",
            fontSize = 17.sp,
            color = Color(0xFF388E3C),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 6.dp)
        )
        ItemPrinsipReuce(
            navController = navController,
            title = "Ide Kreatif untuk Menggunakan Ulang Barang Bekas",
            iconRes = R.drawable.penting,
            borderColor = Color(0xFF4CAF50),
            onClick = { navController.navigate("barangbekas") }
        )
        ItemPrinsipReuce(
            navController = navController,
            title = "Memanfaatkan Barang Bekas di Rumah",
            iconRes = R.drawable.tips,
            borderColor = Color(0xFF4CAF50),
            onClick = { /* Handle click */ }
        )
        ItemPrinsipReuce(
            navController = navController,
            title = "Kebiasaan Sehari-hari untuk Menggunakan Ulang",
            iconRes = R.drawable.kebiasaan,
            borderColor = Color(0xFF4CAF50),
            onClick = { /* Handle click */ }
        )
    }
}



@Composable
fun ItemPrinsipReuce(navController: NavController, title: String, iconRes: Int, borderColor: Color, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .border(BorderStroke(1.dp, borderColor), shape = RoundedCornerShape(8.dp))
            .padding(12.dp)
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier.size(70.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = title,
                    fontSize = 17.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(15.dp))
}





