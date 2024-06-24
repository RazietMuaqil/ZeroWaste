package com.example.zerowaste.pilahsampah

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
fun plastik(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color(0xFF4CAF50),
                contentColor = Color.White,
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart // Posisi teks di awal dengan padding tambahan
                    ) {
                        Text(
                            text = "Pilah Sampah Plastik",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(start = 24.dp), // Tambahkan padding di sini
                            textAlign = TextAlign.Center
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("pilahsampah") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        PlasticList(modifier = Modifier.padding(innerPadding), navController)
    }
}

@Composable
fun PlasticList(modifier: Modifier = Modifier, navController: NavHostController) {
    val items = listOf(
        Triple(R.drawable.pete1, "Plastik PETE", "Plastik PETE merupakan plastik transparan, ringan, dan kuat."),
        Triple(R.drawable.hdpe2, "Plastik HDPE", "Plastik HDPE merupakan plastik yang dapat didaur ulang."),
        Triple(R.drawable.pvc3, "Plastik PVC", "Plastik PVC merupakan plastik yang biaya produksinya rendah."),
        Triple(R.drawable.ldpe4, "Plastik LDPE", "Benda-benda yang dapat didaur ulang."),
        Triple(R.drawable.pp5, "Plastik PP", "Benda-benda yang dapat didaur ulang."),
        Triple(R.drawable.ps6, "Plastik PS", "Benda-benda yang tidak dapat didaur ulang."),
        Triple(R.drawable.other7, "Plastik Other", "Benda-benda yang tidak dapat didaur ulang.")
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp) // Menambahkan jarak antar item
    ) {
        items.forEach { (iconRes, title, description) ->
            item {
                PlasticItem(
                    iconRes = iconRes,
                    title = title,
                    description = description,
                    onClick = {
                        if (title == "Plastik PETE") {
                            navController.navigate("plastikPETE")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun PlasticItem(iconRes: Int, title: String, description: String, onClick: () -> Unit) {
    val borderColor = when (iconRes) {
        R.drawable.pete1 -> Color(0xFF388E3C) // Warna untuk Plastik PETE
        R.drawable.hdpe2 -> Color(0xFFFFA000) // Warna untuk Plastik HDPE
        R.drawable.pvc3 -> Color(0xFF2C2C2C) // Warna untuk Plastik PVC
        R.drawable.ldpe4 -> Color(0xFF7B1FA2) // Warna untuk Plastik LDPE
        R.drawable.pp5 -> Color(0xFFB11C1C) // Warna untuk Plastik PP
        R.drawable.ps6 -> Color(0xFF1976D2) // Warna untuk Plastik PS
        R.drawable.other7 -> Color(0xFF4DD353) // Warna untuk Plastik Other
        else -> Color.Transparent
    }

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        border = BorderStroke(1.dp, borderColor),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
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
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = title, fontSize = 15.sp, color = Color.Black)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = description, fontSize = 12.sp, color = Color.Gray)
            }
            Icon(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = "Arrow",
                tint = Color.Gray
            )
        }
    }
}
