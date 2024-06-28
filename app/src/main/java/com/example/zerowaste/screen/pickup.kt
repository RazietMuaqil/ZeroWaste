package com.example.zerowaste.screen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
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
import java.util.*

@Composable
fun pickup(navController: NavController) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = "Informasi Penjemputan",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
                .padding(15.dp)
        ) {
            var phoneNumber by remember { mutableStateOf("") }
            var address by remember { mutableStateOf("") }
            var pickupDate by remember { mutableStateOf("") }
            var pickupTime by remember { mutableStateOf("") }
            var nama by remember { mutableStateOf("") }
            var kategori by remember { mutableStateOf("") }
            var berat by remember { mutableStateOf("") }
            var harga by remember { mutableStateOf("") }

            var showDatePickerDialog by remember { mutableStateOf(false) }
            var showTimePickerDialog by remember { mutableStateOf(false) }

            PickupInfoInputField(
                value = nama,
                onValueChange = { nama = it },
                label = "Nama Pengguna",
                borderColor = Color(0xFF388E3C),
            )
            PickupInfoDropdownField(
                selectedValue = kategori,
                onValueChange = { kategori = it },
                label = "Kategori Sampah",
                items = listOf("Sampah Organik", "Sampah Anorganik", "Campuran"),
                borderColor = Color(0xFF388E3C)
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                PickupInfoInputField(
                    value = berat,
                    onValueChange = { berat = it },
                    label = "Berat",
                    modifier = Modifier.weight(1f),
                    borderColor = Color(0xFF388E3C),
                    unit = "(Kg)"
                )
                Spacer(modifier = Modifier.width(10.dp))
                PickupInfoInputField(
                    value = harga,
                    onValueChange = { harga = it },
                    label = "Harga",
                    modifier = Modifier.weight(1f),
                    borderColor = Color(0xFF388E3C),
                    unit = "(Per Kg)"
                )
            }

            PickupInfoInputField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = "No. HP",
                borderColor = Color(0xFF388E3C)
            )
            PickupInfoInputField(
                value = address,
                onValueChange = { address = it },
                label = "Alamat",
                borderColor = Color(0xFF388E3C),
                hasEditButton = true,
                navController = navController
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
                borderColor = Color(0xFF388E3C)
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
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    navController.navigate("berhasil")
                    Log.i("Pickup", "Nama: $nama, Kategori: $kategori, Berat: $berat, Harga: $harga, Telepon: $phoneNumber, Alamat: $address, Tanggal: $pickupDate, Waktu: $pickupTime")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF388E3C)),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(
                    text = "Jemput Sampah",
                    color = Color.White,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(vertical = 8.dp),
                )
            }
        }
    }
}

@Composable
fun PickupInfoInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    hasIcon: Boolean = false,
    icon: Int = 0,
    onClick: (() -> Unit)? = null,
    borderColor: Color = Color(0xFF388E3C),
    hasEditButton: Boolean = false,
    navController: NavController? = null,
    unit: String = ""
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier),
        singleLine = true,
        trailingIcon = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (unit.isNotEmpty()) {
                    Text(unit, color = Color.Gray, fontSize = 16.sp, modifier = Modifier.padding(end = 8.dp)) // Increased font size
                }
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
                if (hasEditButton) {
                    Button(
                        onClick = {
                            navController?.navigate("alamat")
                        },
                        colors = ButtonDefaults.buttonColors(Color(0xFF388E3C)),
                        contentPadding = PaddingValues(0.dp),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.padding(end = 7.dp)
                    ) {
                        Text("Edit", color = Color.White)
                    }
                }
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor
        )
    )
}

@Composable
fun PickupInfoDropdownField(
    selectedValue: String,
    onValueChange: (String) -> Unit,
    label: String,
    items: List<String>,
    borderColor: Color = Color(0xFF388E3C)
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        OutlinedTextField(
            value = selectedValue,
            onValueChange = {},
            label = { Text(label) },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true },
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.clickable { expanded = true }
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor
            )
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        onValueChange(item)
                        expanded = false
                    }
                ) {
                    Text(text = item)
                }
            }
        }
    }
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
