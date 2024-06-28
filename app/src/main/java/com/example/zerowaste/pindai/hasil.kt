package com.example.zerowaste.pindai

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.zerowaste.R

@Composable
fun hasil(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
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
                        Spacer(modifier = Modifier.width(48.dp))
                        androidx.compose.material.Text(
                            text = "Kenali Sampah",
                            fontSize = 20.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(48.dp))
                    }
                }
            },
            navigationIcon = {
                androidx.compose.material.IconButton(onClick = { navController.navigate("home")}) {
                    androidx.compose.material.Icon(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = "Back"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        androidx.compose.material.Text(
            text = "Hasil",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF000000),
            modifier = Modifier.padding(start = 20.dp, top = 10.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, bottom = 5.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.hasilpvc),
                    contentDescription = "Gambar Sampah",
                    modifier = Modifier
                        .height(190.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(15.dp)) // Reduced height
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.Green)
                    .padding(8.dp),
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Plastik PVC",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Botol Plastik",
                fontSize = 16.sp,
                color = Color.DarkGray,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(vertical = 2.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(BorderStroke(1.dp, Color(0xFF4CAF50)), shape = RoundedCornerShape(12.dp))
                    .background(Color(0xFFFFFFFF))
                    .padding(16.dp)
            ) {
                Text(
                    text = "PVC, atau plastik jenis vinyl, sangat berguna karena bisa digunakan dalam berbagai hal, seperti tirai, jendela, dan bahkan pakaian tahan air. Ini kuat, ringan, dan tahan air. Namun, kita perlu memastikan untuk membuangnya dengan benar karena jika tidak, bisa merusak lingkungan.",
                    fontSize = 14.sp
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color.Green)
                            .padding(8.dp),
                    )
                    Text(
                        text = "Bisa daur ulang",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color.Red)
                            .padding(8.dp),
                    )
                    Text(
                        text = "Tidak bisa daur ulang",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}
