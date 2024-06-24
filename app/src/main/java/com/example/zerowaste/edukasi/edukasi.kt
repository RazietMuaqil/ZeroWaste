package com.example.zerowaste.edukasi

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.zerowaste.R

@Composable
fun edukasi(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color(0xFF4CAF50),
                contentColor = Color.White,
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Edukasi",
                            fontSize = 20.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 48.dp),  // Disesuaikan untuk menyeimbangkan padding dari ikon navigasi
                            textAlign = TextAlign.Center
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        PrinsipList(modifier = Modifier.padding(innerPadding), navController)
    }
}

@Composable
fun PrinsipList(modifier: Modifier = Modifier, navController: NavHostController) {
    val items = listOf(
        Triple(R.drawable.reduce, "Prinsip Reduce", "Plastik PETE merupakan plastik transparan, ringan, dan kuat."),
        Triple(R.drawable.reuce, "Prinsip Reuce", "Plastik PETE merupakan plastik transparan, ringan, dan kuat."),
        Triple(R.drawable.recycle, "Prinsip Recycle", "Plastik PETE merupakan plastik transparan, ringan, dan kuat."),
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)  // Jarak antar item diperbesar
    ) {
        item {
            Spacer(modifier = Modifier.height(5.dp))  // Menambahkan jarak di atas item pertama
        }
        items.forEachIndexed { index, (iconRes, title, description) ->
            item {
                PrinsipItem(
                    iconRes = iconRes,
                    title = title,
                    description = description,
                    onClick = {
                        when (title) {
                            "Prinsip Reduce" -> navController.navigate("reduce")
                            "Prinsip Reuce" -> navController.navigate("reuce")
                            "Prinsip Recycle" -> navController.navigate("recycle")
                        }
                    },
                    borderColor = when (index) {
                        0 -> Color(0xFF388E3C)
                        1 -> Color(0xFFFF9800)
                        2 -> Color(0xFF03A9F4)
                        else -> Color.Gray
                    }
                )
            }
        }
    }
}

@Composable
fun PrinsipItem(
    iconRes: Int,
    title: String,
    description: String,
    onClick: () -> Unit,
    borderColor: Color
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 6.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable(onClick = onClick)
            .border(BorderStroke(1.dp, borderColor), shape = RoundedCornerShape(8.dp))
    ) {
        Row(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier.size(50.dp)  // Ukuran gambar disesuaikan
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = title, fontSize = 20.sp, color = Color.Black)  // Ukuran font ditambah
                Spacer(modifier = Modifier.height(8.dp))  // Jarak antara judul dan deskripsi ditambah
                Text(text = description, fontSize = 15.sp, color = Color.Gray)  // Ukuran font ditambah
            }
            Icon(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = "Arrow",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)  // Ukuran ikon panah disesuaikan
            )
        }
    }
}
