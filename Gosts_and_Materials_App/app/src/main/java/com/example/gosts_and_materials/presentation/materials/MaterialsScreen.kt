package com.example.gosts_and_materials.presentation.materials

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gosts_and_materials.domain.model.gosts.GostItem
import com.example.gosts_and_materials.domain.model.materials.MaterialItem
import com.example.gosts_and_materials.presentation.destinations.GostItemInfoScreenDestination
import com.example.gosts_and_materials.presentation.destinations.MaterialItemInfoScreenDestination
import com.example.gosts_and_materials.presentation.materials.composables.ExpandedMaterialListItemView
import com.example.gosts_and_materials.presentation.materials.composables.MaterialListItemView
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
@Destination
fun MaterialsScreen(
    navigator: DestinationsNavigator,
    viewModel: MaterialsViewModel = hiltViewModel()
) {

    val state = viewModel.state

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        itemsIndexed(items = state.materials, itemContent = { _, item ->
            Column(content = {
                ExpandedMaterialListItemView(
                    item,
                    onButtonClicked = { index: Int, expanded: Boolean ->
                        viewModel.changeItemValue(
                            index,
                            expanded = expanded
                        )
                    })

                if (item.expanded) {
                    MaterialListItemView(items = item.items,
                        onButtonClicked = { item: MaterialItem ->
                            navigator.navigate(
                                MaterialItemInfoScreenDestination(
                                    item
                                )
                            )
                        }
                    )
                }

                if (item != state.materials.last()) {
                    Divider(
                        modifier = Modifier.padding(
                            horizontal = 16.dp
                        )
                    )
                }
            })
        })
    }
}