package com.example.zerowaste.screen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.zerowaste.R
import java.util.Calendar

data class WasteType(val name: String, val icon: Int)

@Composable
fun pickup(navController: NavController? = null) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = "Pickup",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Informasi Sampah",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(7.dp))
        Text(
            text = "Silakan pilih jenis sampah dan masukkan perkiraan berat sampah Anda. Tidak ada batasan berat minimum untuk pengambilan.",
            fontSize = 14.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(10.dp))

        val wasteTypes = listOf(
            WasteType("Organik", R.drawable.organik),
            WasteType("Anorganik", R.drawable.anorganik),
            WasteType("Campuran", R.drawable.campuran)
        )

        wasteTypes.forEach { wasteType ->
            Spacer(modifier = Modifier.height(3.dp))
            WasteTypeButton(text = wasteType.name, iconRes = wasteType.icon)
        }

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Informasi Penjemputan",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(5.dp))

        var phoneNumber by remember { mutableStateOf("") }
        var address by remember { mutableStateOf("") }
        var pickupDate by remember { mutableStateOf("") }
        var pickupTime by remember { mutableStateOf("") }

        var showDatePickerDialog by remember { mutableStateOf(false) }
        var showTimePickerDialog by remember { mutableStateOf(false) }

        PickupInfoInputField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = "Nomor Telepon",
            borderColor = Color(0xFF388E3C)
        )
        PickupInfoInputField(
            value = address,
            onValueChange = { address = it },
            label = "Alamat",
            borderColor = Color(0xFF388E3C)
        )
        PickupInfoInputField(
            value = pickupDate,
            onValueChange = { pickupDate = it },
            label = "Tanggal Penjemputan",
            hasIcon = true,
            icon = R.drawable.calendar,
            onClick = { showDatePickerDialog = true },
            borderColor = Color(0xFF388E3C)
        )
        PickupInfoInputField(
            value = pickupTime,
            onValueChange = { pickupTime = it },
            label = "Waktu Penjemputan",
            hasIcon = true,
            icon = R.drawable.drop,
            onClick = { showTimePickerDialog = true },
            borderColor = Color(0xFF4CAF50) // Warna border hijau
        )

        if (showDatePickerDialog) {
            showDatePicker { date ->
                pickupDate = date
                showDatePickerDialog = false
            }
        }

        if (showTimePickerDialog) {
            showTimePicker { time ->
                pickupTime = time
                showTimePickerDialog = false
            }
        }
    }
}

@Composable
fun WasteTypeButton(text: String, iconRes: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .border(1.dp, Color(0xFF388E3C), shape = RoundedCornerShape(8.dp))
            .background(Color(0x124CAF50), shape = RoundedCornerShape(8.dp))
            .padding(10.dp)
            .clickable { }
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = text,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            fontSize = 18.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Button(
            onClick = { /* Handle click */ },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF388E3C),
                contentColor = Color.White
            ),
            modifier = Modifier
                .defaultMinSize(minHeight = 36.dp)
        ) {
            Text(text = "Pilih")
        }
    }
}

@Composable
fun PickupInfoInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    hasIcon: Boolean = false,
    icon: Int = 0,
    onClick: (() -> Unit)? = null,
    borderColor: Color = Color(0xFF388E3C) // Warna hijau sebagai default
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier), // Hanya tambahkan clickable jika onClick disediakan
        singleLine = true,
        trailingIcon = {
            if (hasIcon) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onClick?.invoke() }
                )
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor
        )
    )
}


@Composable
fun showDatePicker(onDateSelected: (String) -> Unit) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            onDateSelected("$dayOfMonth/${month + 1}/$year")
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}

@Composable
fun showTimePicker(onTimeSelected: (String) -> Unit) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    TimePickerDialog(
        context,
        { _, hour, minute ->
            onTimeSelected("$hour:$minute")
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        true
    ).show()
}
