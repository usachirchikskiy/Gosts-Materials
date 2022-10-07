package com.example.gosts_and_materials.presentation.item_gost_info

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gosts_and_materials.domain.model.gosts.GostItem
import com.example.gosts_and_materials.downloadfile.File
import com.example.gosts_and_materials.presentation.item_gost_info.composables.GostItemView
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun GostItemInfoScreen(
    itemGost: GostItem,
    //startDownload: (File) -> Unit
) {
    Log.d("GostItemInfoScreen", "$itemGost")
    LazyColumn() {
        items(itemGost.items.size) { i ->
            GostItemView(
                item = itemGost.items[i],
//                startDownload = { file ->
//                    startDownload(file)
//                }
            )
            if (i < itemGost.items.size) {
                Divider(
                    modifier = Modifier.padding(
                        horizontal = 16.dp
                    )
                )
            }
        }
    }
}


