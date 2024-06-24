package com.example.zerowasteproject.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app.R

@Composable
fun home(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF388E3C))
    ) {
        CustomBackground()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "ZeroWaste",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .offset(x = 8.dp, y = (-5).dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 20.dp))
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .background(Color(0x66E7E7E7), shape = RoundedCornerShape(8.dp))
                            .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(8.dp))
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.poin),
                                    contentDescription = "Add Points",
                                    modifier = Modifier.size(25.dp),
                                    tint = Color(0xFFFF9800)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Points",
                                    fontSize = 13.sp,
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
                                    contentDescription = "Points Icon",
                                    modifier = Modifier.size(25.dp)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Selamat Malam",
                            fontSize = 16.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "John David!",
                            fontSize = 16.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Yuk, Mulai Petualangan Memilah Sampah",
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            Column {
                MenuItem(
                    title = "Kenali Sampah",
                    description = "Untuk melihat jenis sampahmu",
                    iconRes = R.drawable.pindai,
                    borderColor = Color(0xFF388E3C),
                    onClick = { navController.navigate("pindai") }
                )
                MenuItem(
                    title = "Pilah Sampah",
                    description = "Sampah yang kami ambil",
                    iconRes = R.drawable.pilah,
                    borderColor = Color(0xFF2196F3),
                    onClick = { navController.navigate("pilahsampah") }
                )
                MenuItem(
                    title = "Tukar Poin",
                    description = "Ayo tukarkan poinmu",
                    iconRes = R.drawable.tukar,
                    borderColor = Color(0xFFFF9800),
                    onClick = { navController.navigate("poin") }
                )
                MenuItem(
                    title = "Edukasi",
                    description = "Prinsip 3R (Reduce, Reuse, Recycle)",
                    iconRes = R.drawable.edukasi,
                    borderColor = Color(0xFFD32F2F),
                    onClick = { navController.navigate("edukasi") }
                )
            }
        }
    }
}

@Composable
fun CustomBackground() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height
        val cornerRadius = 20.dp.toPx()

        val path = Path().apply {
            moveTo(0f, height * 0.3f + cornerRadius)
            arcTo(
                rect = Rect(0f, height * 0.3f, cornerRadius * 2, height * 0.3f + cornerRadius * 2),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )
            lineTo(width - cornerRadius, height * 0.3f)
            arcTo(
                rect = Rect(width - cornerRadius * 2, height * 0.3f, width, height * 0.3f + cornerRadius * 2),
                startAngleDegrees = 270f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )
            lineTo(width, height)
            lineTo(0f, height)
            close()
        }

        drawRect(
            color = Color(0xFF388E3C),
            topLeft = Offset(0f, 0f),
            size = Size(width, height * 0.3f)
        )

        drawPath(
            path = path,
            color = Color.White
        )
    }
}


@Composable
fun MenuItem(title: String, description: String, iconRes: Int, borderColor: Color, onClick: () -> Unit) {
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
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 17.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = "Arrow",
                tint = Color.Gray,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
    Spacer(modifier = Modifier.height(15.dp))
}