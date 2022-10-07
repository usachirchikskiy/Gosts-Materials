package com.example.gosts_and_materials.presentation.search.types.resultPage.composables

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

@Composable
fun SearchListItemView(
    items: List<String>,
    onButtonClicked: ((item: String) -> Unit)? = null
) {
    for (item in items) {
        Column(content = {
            Text(text = item, color = Color.Black)
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
