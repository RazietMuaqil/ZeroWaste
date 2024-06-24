package com.example.zerowaste.pilahsampah

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import androidx.navigation.NavHostController
import com.example.zerowaste.R

@Composable
fun plastikPETE(navController: NavHostController) {
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
                            Spacer(modifier = Modifier.width(48.dp))  // Spacer untuk mengkompensasi lebar ikon navigasi
                            Text(
                                text = "Plastik PETE",
                                fontSize = 20.sp,
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.width(48.dp))  // Spacer untuk menyeimbangkan tampilan
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("plastik") }) {
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
                VideoSection()
                InstructionSection()
            }
        }
    )
}

@Composable
fun VideoSection() {
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
                color = Color(0xFF4CAF50),
                modifier = Modifier.padding(5.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Image(
                painter = painterResource(R.drawable.video),
                contentDescription = "Video Thumbnail",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)  // Sesuaikan tinggi gambar untuk memperbesarnya
            )
        }
    }
}

@Composable
fun InstructionSection() {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(start = 4.dp, top = 8.dp)
    ) {
        Text(
            text = "Cara Memilah Plastik PETE",
            fontSize = 20.sp,
            color = Color(0xFF4CAF50),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        InstructionItem("1. Identifikasi : Cari kode daur ulang \"1\" pada plastik untuk mengenali PETE.")
        InstructionItem("2. Bersihkan : Pastikan plastik bersih dari sisa-sisa makanan atau cairan.")
        InstructionItem("3. Pisahkan : Pisahkan plastik PETE dari sampah lainnya.")
        InstructionItem("4. Keringkan : Pastikan plastik kering sebelum dibuang.")
        InstructionItem("5. Simpan dengan Rapi : Tempatkan plastik PETE dalam wadah terpisah.")
        InstructionItem("6. Daur Ulang : Temukan program daur ulang plastik PETE di daerah Anda.")
    }
}


@Composable
fun InstructionItem(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}




