package com.example.zerowaste.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.zerowaste.edukasi.*
import com.example.zerowaste.login.login
import com.example.zerowaste.pilahsampah.*
import com.example.zerowaste.pindai.hasil
import com.example.zerowaste.pindai.pindai
import com.example.zerowaste.poin.poin
import com.example.zerowaste.screen.*
import com.example.zerowaste.signup.signup

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val selectedItem = remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            if (
                currentDestination?.route !in listOf(
                    Screens.Login.name,
                    Screens.Signup.name,
                    Screens.Pilahsampah.name,
                    Screens.Plastik.name,
                    Screens.Kertas.name,
                    Screens.PlastikPETE.name,
                    Screens.Poin.name,
                    Screens.Edukasi.name,
                    Screens.Reduce.name,
                    Screens.Reuce.name,
                    Screens.Recycle.name,
                    Screens.Edukasisampah.name,
                    Screens.Barangbekas.name,
                    Screens.Daurulang.name,
                    Screens.Splash.name,
                    Screens.Berhasil.name,
                    Screens.Alamat.name,
                    Screens.Simpan.name,
                    Screens.Hasil.name
                )
            )
                BottomNavigation(
                    backgroundColor = Color.White,
                    contentColor = Color.Gray
                ) {
                    listOfNavItems.forEachIndexed { index, navItem ->
                        BottomNavigationItem(
                            icon = {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier.padding(bottom = 2.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = navItem.icon),
                                        contentDescription = navItem.label,
                                        tint = if (selectedItem.value == index) Color(0xFF388E3C) else Color.Gray,
                                        modifier = Modifier.size(27.dp)
                                    )
                                }
                            },
                            label = {
                                Text(
                                    text = navItem.label,
                                    color = if (selectedItem.value == index) Color(0xFF388E3C) else Color.Gray,
                                    fontSize = 12.sp
                                )
                            },
                            selected = currentDestination?.hierarchy?.any { it.route == navItem.route } == true,
                            onClick = {
                                selectedItem.value = index
                                navController.navigate(navItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.Splash.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = Screens.Home.name) {
                home(navController = navController)
            }
            composable(route = Screens.Pickup.name) {
                pickup(navController = navController)
            }
            composable(route = Screens.Notification.name) {
                notification(navController = navController)
            }
            composable(route = Screens.Profile.name) {
                profile(navController = navController)
            }
            composable(route = Screens.Login.name) {
                login(navController = navController)
            }
            composable(route = Screens.Signup.name) {
                signup(navController = navController)
            }
            composable(route = Screens.Plastik.name) {
                plastik(navController = navController)
            }
            composable(route = Screens.Pilahsampah.name) {
                pilahsampah(navController = navController)
            }
            composable(route = Screens.Kertas.name) {
                kertas(navController = navController)
            }
            composable(route = Screens.PlastikPETE.name) {
                plastikPETE(navController = navController)
            }
            composable(route = Screens.Poin.name) {
                poin(navController = navController)
            }
            composable(route = Screens.Edukasi.name) {
                edukasi(navController = navController)
            }
            composable(route = Screens.Reduce.name) {
                reduce(navController = navController)
            }
            composable(route = Screens.Reuce.name) {
                reuce(navController = navController)
            }
            composable(route = Screens.Recycle.name) {
                recycle(navController = navController)
            }
            composable(route = Screens.Edukasisampah.name) {
                edukasisampah(navController = navController)
            }
            composable(route = Screens.Barangbekas.name) {
                barangbekas(navController = navController)
            }
            composable(route = Screens.Daurulang.name) {
                daurulang(navController = navController)
            }
            composable(route = Screens.Splash.name) {
                splash(navController = navController)
            }
            composable(route = Screens.Pindai.name) {
                pindai(navController = navController)
            }
            composable(route = Screens.Berhasil.name) {
                berhasil(navController = navController)
            }
            composable(route = Screens.Alamat.name) {
                alamat(navController = navController)
            }
            composable(route = Screens.Simpan.name) {
                simpan(navController = navController)
            }
            composable(route = Screens.Hasil.name) {
                hasil(navController = navController)
            }
        }
    }
}
