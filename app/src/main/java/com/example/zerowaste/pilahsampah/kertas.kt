package com.example.zerowasteproject.pilahsampah

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
import com.example.app.R

@Composable
fun kertas(navController: NavHostController) {
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
                            text = "Pilah Sampah Kertas",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(start = 24.dp), // Tambahkan padding di sini
                            textAlign = TextAlign.Center
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        KertasList(modifier = Modifier.padding(innerPadding))
    }
}


@Composable
fun KertasList(modifier: Modifier = Modifier) {
    val items = listOf(
        Triple(R.drawable.karton, "Karton", "Plastik PETE merupakan plastik transparan, ringan, dan kuat."),
        Triple(R.drawable.potkertas, "Potongan Kertas", "Plastik HDPE merupakan plastik yang dapat didaur ulang."),
        Triple(R.drawable.pamflet, "Pamflet", "Plastik PVC merupakan plastik yang biaya produksinya rendah."),
        Triple(R.drawable.kemasan, "Kemasan Kertas", "Benda-benda yang dapat didaur ulang."),
        Triple(R.drawable.buku, "Buku", "Benda-benda yang dapat didaur ulang."),
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items.forEach { (iconRes, title, description) ->
            item {
                KertasItem(
                    iconRes = iconRes,
                    title = title,
                    description = description,
                    onClick = { /* Handle item click */ }
                )
            }
        }
    }
}

@Composable
fun KertasItem(iconRes: Int, title: String, description: String, onClick: () -> Unit) {
    val borderColor = when (iconRes) {
        R.drawable.karton -> Color(0xFF388E3C)
        R.drawable.potkertas -> Color(0xFFFFA000)
        R.drawable.pamflet -> Color(0xFF2C2C2C)
        R.drawable.kemasan -> Color(0xFF303F9F)
        R.drawable.buku -> Color(0xFFB11C1C)
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
                Text(text = title,
                     fontSize = 15.sp,
                     color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = description,
                     fontSize = 12.sp,
                     color = Color.Gray)
            }
            Icon(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = "Arrow",
                tint = Color.Gray
            )
        }
    }
}
