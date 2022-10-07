package com.example.gosts_and_materials.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Work
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.gosts_and_materials.presentation.destinations.GostScreenDestination
import com.example.gosts_and_materials.presentation.destinations.MaterialsScreenDestination
import com.example.gosts_and_materials.presentation.destinations.SearchScreenDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec


//sealed class BottomNavItem(val name:String, val icon:ImageVector,val route:String){
//    object Gosts : BottomNavItem(name, icon,route)
//    object Materials: BottomNavItem(name, icon,route)
////    object Search: BottomNavItem(name, icon,route)
//}

enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
    val icon: ImageVector,
    val label: String
) {
    GostScreen(GostScreenDestination, Icons.Default.Home, "ГОСТ"),
    MaterialsScreen(MaterialsScreenDestination,Icons.Default.Work,"Материалы"),
    SearchScreen(SearchScreenDestination,Icons.Default.Search,"Поиск")
//    Feed(FeedScreenDestination, Icons.Default.Email, R.string.feed_screen),
}