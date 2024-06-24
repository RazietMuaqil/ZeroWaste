package com.example.zerowaste.edukasi

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.zerowaste.R

@Composable
fun edukasisampah(navController: NavHostController) {
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
                                .padding(end = 48.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("reduce") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Back"
                        )
                    }
                }
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
                GambarEdukasi()
                InstructionEdukasi()
            }
        }
    )
}

@Composable
fun GambarEdukasi() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            Image(
                painter = painterResource(R.drawable.kardus),
                contentDescription = "Poster Edukasi",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
    }
}

@Composable
fun InstructionEdukasi() {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Pentingnya Mengurangi Sampah",
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Mari kita jelajahi mengapa mengurangi sampah itu penting dan mengapa hal ini dapat membuat perbedaan besar bagi lingkungan dan kesehatan kita:",
            fontSize = 13.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "1. Lingkungan yang Bersih dan Sehat",
            fontSize = 13.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "2. Mencegah Penyakit",
            fontSize = 13.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "3. Hemat Sumber Daya Alam",
            fontSize = 13.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "4. Iklim yang Lebih Baik",
            fontSize = 13.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "5. Menyelamatkan Satwa Liar",
            fontSize = 13.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Mengurangi sampah itu lebih dari sekadar tindakan kecil. Ini adalah cara kita berkontribusi dalam menjaga bumi kita tetap indah dan layak huni. Ayo mulai dengan langkah kecil dan bersama-sama kita bisa membuat perubahan besar!",
            fontSize = 13.sp,
            color = Color.Black
        )
    }
}






