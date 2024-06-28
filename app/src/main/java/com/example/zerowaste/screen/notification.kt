package com.example.zerowaste.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable



data class Notification(
    val title: String,
    val message: String,
    val date: String
)

@Composable
fun notification(navController: NavController) {
    val notifications = listOf(
        Notification(
            title = "Ingat Memilah Sampah dengan Benar",
            message = "Pastikan untuk memilah sampah dengan benar. Terima kasih!",
            date = "08/05/2024"
        ),
        Notification(
            title = "Waktu Pengumpulan Sampah!",
            message = "Jangan lupa untuk menyiapkan sampah Anda hari ini. Terima kasih!",
            date = "05/05/2024"
        ),
        Notification(
            title = "Perubahan Jadwal",
            message = "Jadwal pengumpulan sampah telah diperbarui. Mohon periksa aplikasi kami untuk informasi terbaru",
            date = "04/05/2024"
        ),
        Notification(
            title = "Selamat! Sampah Anda Sudah Diambil",
            message = "Sampah Anda telah berhasil dikumpulkan. Terima kasih atas kerjasamanya!",
            date = "03/05/2024"
        ),
        Notification(
            title = "Waktu Pengumpulan Sampah!",
            message = "Jangan lupa untuk mengeluarkan sampah hari ini.",
            date = "02/05/2024"
        ),
        Notification(
            title = "Perubahan Jadwal",
            message = "Jadwal pengumpulan sampah telah diperbarui. Mohon periksa aplikasi kami untuk informasi terbaru",
            date = "01/05/2024"
        )
    )

    var selectedNotification by remember { mutableStateOf<Notification?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = "Notification",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn {
            items(notifications) { notification ->
                NotificationCard(notification) {
                    selectedNotification = notification
                }
            }
        }

        selectedNotification?.let { notification ->
            NotificationDialog(
                notification = notification,
                onDismiss = { selectedNotification = null }
            )
        }
    }
}

@Composable
fun NotificationCard(notification: Notification, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .background(Color(0x124CAF50), shape = RoundedCornerShape(8.dp))
            .border(1.dp, Color(0xFF388E3C), RoundedCornerShape(8.dp)) // Menambahkan border hijau tua
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = notification.title,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color(0xFF000000)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = notification.message,
                fontSize = 13.sp,
                color = Color(0xFF000000)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = notification.date,
                fontSize = 13.sp,
                color = Color(0xFF000000),
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}

@Composable
fun NotificationDialog(notification: Notification, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = notification.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        },
        text = {
            Text(
                text = notification.message,
                fontSize = 16.sp,
                color = Color.Black
            )
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C)),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .height(36.dp)
            ) {
                Text("OK", color = Color.White, fontSize = 16.sp)
            }
        },
        modifier = Modifier
            .background(Color(0xFF388E3C), RoundedCornerShape(7.dp))
            .padding(2.dp),
        shape = RoundedCornerShape(7.dp),
        containerColor = Color(0xE6FFFFFF)
    )
}
