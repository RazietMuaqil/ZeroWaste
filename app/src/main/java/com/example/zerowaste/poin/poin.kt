package com.example.zerowaste.poin

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.zerowaste.R


@Composable
fun poin(navController: NavController) {
    androidx.compose.material.Scaffold(
        topBar = {
            androidx.compose.material.TopAppBar(
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
                            androidx.compose.material.Text(
                                text = "Penukaran Poin",
                                fontSize = 20.sp,
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.width(48.dp))  // Spacer untuk menyeimbangkan tampilan
                        }
                    }
                },
                navigationIcon = {
                    androidx.compose.material.IconButton(onClick = { navController.navigate("home") }) {
                        androidx.compose.material.Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Kembali"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        KotakPoin(modifier = Modifier.padding(innerPadding), navController)
    }
}

@Composable
fun KotakPoin(modifier: Modifier = Modifier, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 4.dp),  // Mengurangi padding vertikal
        verticalArrangement = Arrangement.Top,  // Memulai konten dari atas
        horizontalAlignment = Alignment.CenterHorizontally  // Mengubah alignment horizontal ke center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)  // Membuat Box selebar 80% layar
                .height(150.dp)  // Meningkatkan tinggi Box
                .background(Color.White, shape = RoundedCornerShape(20.dp))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(Color(0x66E7E7E7), shape = RoundedCornerShape(8.dp))
                        .border(BorderStroke(2.dp, Color.Gray), shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 18.dp, vertical = 14.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.poin),
                                contentDescription = "Tambahkan Poin",
                                modifier = Modifier.size(40.dp),
                                tint = Color(0xFFFF9800)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Points",
                                fontSize = 17.sp,
                                color = Color(0xFF1B1B1B),
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "100",
                                fontSize = 22.sp,
                                color = Color(0xFF1B1B1B),
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Image(
                                painter = painterResource(id = R.drawable.plus),
                                contentDescription = "Ikon Poin",
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                }
            }
        }
        Column {
            Text(
                text = "Penukaran Poin",
                fontSize = 16.sp,
                color = Color(0xFF4CAF50),  // Mengubah warna teks menjadi hijau
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)  // Menambahkan jarak ke bawah
            )
            Spacer(modifier = Modifier.height(10.dp))
            Item(
                title = "Tukar Poin Jadi Uang",
                iconRes = R.drawable.moneybag,
                borderColor = Color(0xFF747474),
                onClick = { /* Handle click */ }
            )
            Item(
                title = "Tukar Sampah Organik Dengan Poin",
                iconRes = R.drawable.organicwaste,
                borderColor = Color(0xFF747474),
                onClick = { /* Handle click */ }
            )
            Item(
                title = "Tukar Sampah Plastik Dengan Poin",
                iconRes = R.drawable.plasticwaste,
                borderColor = Color(0xFF747474),
                onClick = { /* Handle click */ }
            )
        }
    }
}

@Composable
fun Item(title: String, iconRes: Int, borderColor: Color, onClick: () -> Unit) {
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
                modifier = Modifier.size(60.dp)
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
    Spacer(modifier = Modifier.height(25.dp))
}
