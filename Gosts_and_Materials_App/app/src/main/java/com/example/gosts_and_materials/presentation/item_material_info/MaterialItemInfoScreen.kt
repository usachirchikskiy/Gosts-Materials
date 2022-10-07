package com.example.gosts_and_materials.presentation.item_material_info

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gosts_and_materials.domain.model.gosts.GostItem
import com.example.gosts_and_materials.domain.model.materials.MaterialItem
import com.example.gosts_and_materials.domain.model.materials.MaterialItemInfo
import com.example.gosts_and_materials.presentation.destinations.MaterialItemFullInfoDestination
import com.example.gosts_and_materials.presentation.destinations.MaterialItemInfoScreenDestination
import com.example.gosts_and_materials.presentation.item_gost_info.composables.GostItemView
import com.example.gosts_and_materials.presentation.item_material_info.composables.MaterialItemFullInfo
import com.example.gosts_and_materials.presentation.item_material_info.composables.MaterialItemView
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun MaterialItemInfoScreen(
    navigator: DestinationsNavigator,
    itemMaterial: MaterialItem,
) {
//    Log.d("MaterialItemInfoScreen", "$itemMaterial")
    LazyColumn() {
        items(itemMaterial.items.size) { i ->
            MaterialItemView(
                item = itemMaterial.items[i],
                onButtonClicked = { item: MaterialItemInfo ->
                    navigator.navigate(
                        MaterialItemFullInfoDestination(
                            item
                        )
                    )
                }
            )
            if (i < itemMaterial.items.size) {
                Divider(
                    modifier = Modifier.padding(
                        horizontal = 16.dp
                    )
                )
            }
        }
    }
}