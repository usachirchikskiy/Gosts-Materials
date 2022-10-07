package com.example.gosts_and_materials.presentation.gosts.composables

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.gosts_and_materials.domain.model.gosts.GostItem

@Composable
fun GostListItemView(
    items: List<GostItem>,
    onButtonClicked: ((item: GostItem) -> Unit)? = null
) {
    for (item in items) {
        Column(content = {
            Text(text = item.name, color = Color.Black)
        },modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {
                    onButtonClicked?.invoke(item)
                })
            .padding(16.dp))

        if (item != items[items.size-1]) {
            Divider(
                modifier = Modifier.padding(
                    horizontal = 16.dp
                )
            )
        }
    }
}
