package com.example.gosts_and_materials.presentation.item_material_info.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gosts_and_materials.domain.model.gosts.GostItemInfo
import com.example.gosts_and_materials.domain.model.materials.MaterialItem
import com.example.gosts_and_materials.domain.model.materials.MaterialItemInfo

@Composable
fun MaterialItemView(
    item: MaterialItemInfo,
    onButtonClicked: ((item: MaterialItemInfo) -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .clickable(onClick = {
                onButtonClicked?.invoke(item)
            })
    ) {
        Text(
            text = item.material,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = item.description,
            fontSize = 14.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

//        Text(
//            text = item.chemical,
//            fontSize = 14.sp,
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(modifier = Modifier.height(8.dp))

    }
}