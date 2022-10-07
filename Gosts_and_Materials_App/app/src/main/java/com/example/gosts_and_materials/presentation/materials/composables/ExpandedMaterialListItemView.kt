package com.example.gosts_and_materials.presentation.materials.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.gosts_and_materials.domain.model.gosts.Gost
import com.example.gosts_and_materials.domain.model.gosts.GostItem
import com.example.gosts_and_materials.domain.model.materials.Material

@Composable
fun ExpandedMaterialListItemView(
    item: Material,
    onButtonClicked: ((index: Int, expanded: Boolean) -> Unit)? = null
) {
    Row(
        content = {
            Text(item.name, modifier = Modifier.weight(1F), fontWeight = FontWeight.Bold)
            Icon(
                imageVector = if (item.expanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                contentDescription = "name",
                tint = Color.Black, modifier = Modifier
                    .size(30.dp)
                    .clickable(onClick = {
                        onButtonClicked?.invoke(item.id-1, !item.expanded)
                    })
            )
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}