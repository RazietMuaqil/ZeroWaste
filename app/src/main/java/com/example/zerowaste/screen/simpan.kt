package com.example.zerowaste.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.zerowaste.R

@Composable
fun simpan(navController: NavController) {
    var phoneNumber by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var selectedCity by remember { mutableStateOf("") }
    var selectedKecamatan by remember { mutableStateOf("") }
    var selectedKelurahan by remember { mutableStateOf("") }
    var postalCode by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
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
                            text = "Tambah Alamat",
                            fontSize = 20.sp,
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("alamat") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Tambah Alamat",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
            )
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("No. Ponsel") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )

            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Alamat") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(bottom = 8.dp)
            )

            DropdownMenuField(
                label = "Pilih Kota",
                selectedValue = selectedCity,
                onValueChange = { selectedCity = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                items = listOf("Jakarta Timur", "Jakarta Pusat", "Jakarta Barat")
            )

            DropdownMenuField(
                label = "Pilih Kecamatan",
                selectedValue = selectedKecamatan,
                onValueChange = { selectedKecamatan = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                items = listOf("Gambir", "Menteng", "Kemayoran", "Senen", "Tanah Abang")
            )

            DropdownMenuField(
                label = "Pilih Kelurahan",
                selectedValue = selectedKelurahan,
                onValueChange = { selectedKelurahan = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                items = listOf("Kelurahan Gambir", "Kelurahan Kebon Kelapa", "Kelurahan Duri Pulo", "Kelurahan Cideng")
            )

            OutlinedTextField(
                value = postalCode,
                onValueChange = { postalCode = it },
                label = { Text("Kode Pos") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(17.dp))
            Button(
                onClick = { navController.navigate("alamat") },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF388E3C)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Simpan", color = Color.White, fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun DropdownMenuField(
    label: String,
    selectedValue: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    items: List<String> = emptyList()
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = selectedValue,
            onValueChange = {},
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                }
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(onClick = {
                    onValueChange(item)
                    expanded = false
                }) {
                    Text(text = item)
                }
            }
        }
    }
}
