package com.example.zerowaste.pilahsampah

import androidx.compose.foundation.Image
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.zerowaste.R
import com.example.zerowaste.navigation.Screens

@Composable
fun pilahsampah(navController: NavHostController) {
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
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Spacer(modifier = Modifier.width(48.dp))  // Spacer untuk mengkompensasi lebar ikon navigasi
                            Text(
                                text = "Pilah Sampah",
                                fontSize = 20.sp,
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.width(48.dp))  // Spacer untuk menyeimbangkan tampilan
                        }
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
        WasteSortingGrid(modifier = Modifier.padding(innerPadding), navController)
    }
}

@Composable
fun WasteSortingGrid(modifier: Modifier = Modifier, navController: NavHostController) {
    val items = listOf(
        R.drawable.plastik to "Plastik",
        R.drawable.kertas to "Kertas",
        R.drawable.logam to "Logam",
        R.drawable.kaca to "Kaca",
        R.drawable.porganik to "Organik",
        R.drawable.elektronik to "Elektronik",
        R.drawable.berbahaya to "Berbahaya",
        R.drawable.lainnya to "Lainnya"
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items.chunked(2).forEach { rowItems ->
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    rowItems.forEach { (iconRes, label) ->
                        WasteCategoryItem(
                            iconRes = iconRes,
                            label = label,
                            onClick = {
                                when (label) {
                                    "Plastik" -> navController.navigate(Screens.Plastik.name)
                                    "Kertas" -> navController.navigate(Screens.Kertas.name) // Tambahkan navigasi untuk Kertas
                                    // Handle other item clicks if needed
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun WasteCategoryItem(iconRes: Int, label: String, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        modifier = Modifier
            .padding(8.dp)
            .size(150.dp)
            .clickable(onClick = onClick)
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))  // Menambahkan border dengan ketebalan 1dp dan warna abu-abu
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = label, fontSize = 16.sp)
        }
    }
}
