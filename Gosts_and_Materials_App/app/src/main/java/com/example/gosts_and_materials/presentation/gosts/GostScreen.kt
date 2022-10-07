package com.example.gosts_and_materials.presentation.gosts

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
import com.example.gosts_and_materials.presentation.destinations.GostItemInfoScreenDestination
import com.example.gosts_and_materials.presentation.gosts.composables.ExpandedGostListItemView
import com.example.gosts_and_materials.presentation.gosts.composables.GostListItemView
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
@Destination(start = true)
fun GostScreen(
    navigator: DestinationsNavigator,
//    startDownload: (File) -> Unit,
    viewModel: GostsViewModel = hiltViewModel()
) {

    val state = viewModel.state

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        itemsIndexed(items = state.gosts, itemContent = { _, item ->
            Column(content = {
                ExpandedGostListItemView(
                    item,
                    onButtonClicked = { index: Int, expanded: Boolean ->
                        viewModel.changeItemValue(
                            index,
                            expanded = expanded
                        )
                    })

                if (item.expanded) {
                    GostListItemView(items = item.items,
                        onButtonClicked = { item: GostItem ->
                            navigator.navigate(
                                GostItemInfoScreenDestination(
                                    item,
//                                    startDownload = {
//                                        Log.d("GostScreen", "startDownload")
//                                    }
                                )
                            )
                        }
                    )
                }
            })

            if (item!= state.gosts.last()) {
                Divider(
                    modifier = Modifier.padding(
                        horizontal = 16.dp
                    )
                )
            }
        })
    }
}