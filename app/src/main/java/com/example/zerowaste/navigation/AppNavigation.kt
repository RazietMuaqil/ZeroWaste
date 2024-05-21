package com.example.zerowaste.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.zerowaste.login.login
import com.example.zerowaste.screen.home
import com.example.zerowaste.screen.notification
import com.example.zerowaste.screen.pickup
import com.example.zerowaste.screen.profile
import com.example.zerowaste.signup.signup

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(
        bottomBar = {
            if(
                currentDestination?.route != Screens.Login.name && currentDestination?.route != Screens.Signup.name
            )
            NavigationBar {
                listOfNavItems.forEach { navItem ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == navItem.route } == true,
                        onClick = {
                            navController.navigate(navItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Image(
                                painter = painterResource(id = navItem.icon),
                                contentDescription = null,
                                modifier = Modifier.size(25.dp)
                            )
                        },
                        label = {
                            Text(
                                text = navItem.label,
                                fontSize = 10.sp
                            )
                        }
                    )
                }

            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.Login.name,
            modifier = Modifier
                .padding(paddingValues)
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
        }
    }
}