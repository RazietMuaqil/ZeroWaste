package com.example.zerowaste.navigation

import androidx.annotation.DrawableRes
import com.example.zerowaste.R

data class NavItem(
    val label: String,
    @DrawableRes
    val icon: Int,
    val route: String
)

val listOfNavItems = listOf(
    NavItem(
        label = "Home",
        icon = R.drawable.home,
        route = Screens.Home.name
    ),
    NavItem(
        label = "Pickup",
        icon = R.drawable.pickup,
        route = Screens.Pickup.name
    ),
    NavItem(
        label = "Notification",
        icon = R.drawable.notif,
        route = Screens.Notification.name
    ),
    NavItem(
        label = "Account",
        icon = R.drawable.profile,
        route = Screens.Profile.name
    )
)
