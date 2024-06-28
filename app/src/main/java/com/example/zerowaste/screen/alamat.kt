package com.example.zerowaste.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.zerowaste.R

@Composable
fun alamat(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopAppBar(
            backgroundColor = Color(0xFF4CAF50),
            contentColor = Color.White,
            title = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp, end = 35.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Daftar Alamat",
                        fontSize = 20.sp,
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigate("pickup") }) {
                    Icon(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = "Back"
                    )
                }
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            AddressCard(
                address = "Gang asri no. 11, Jl. Pesantren, Cibabat, Kepulauan Seribu Selatan, Kabupaten Kepulauan Seribu, DKI Jakarta, 50513",
                onEditClick = {  }
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { navController.navigate("simpan") },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF388E3C)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Tambah Alamat", color = Color.White, fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun AddressCard(address: String, onEditClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(text = address, fontSize = 16.sp, fontWeight = FontWeight.Normal)
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onEditClick,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF388E3C)),
                modifier = Modifier
                    .align(Alignment.End)
                    .height(36.dp)
            ) {
                Text("Edit", color = Color.White)
            }
        }
    }
}
