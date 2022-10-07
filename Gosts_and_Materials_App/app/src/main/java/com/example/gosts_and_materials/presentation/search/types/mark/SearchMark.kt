package com.example.gosts_and_materials.presentation.search.types.mark

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gosts_and_materials.domain.model.materials.MaterialItemInfo
import com.example.gosts_and_materials.presentation.destinations.MaterialItemFullInfoDestination
import com.example.gosts_and_materials.presentation.item_material_info.composables.MaterialItemView
import com.example.gosts_and_materials.presentation.search.types.mark.SearchMarkViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun SearchMark(
    navigator: DestinationsNavigator,
    viewModel: SearchMarkViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = {
                viewModel.searchMark(it)
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Search...")
            },
            maxLines = 1,
            singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))
        if (state.materials.isNotEmpty()) {
            LazyColumn() {
                items(state.materials.size) { i ->
                    MaterialItemView(
                        item = state.materials[i]!!,
                        onButtonClicked = { item: MaterialItemInfo ->
                            navigator.navigate(
                                MaterialItemFullInfoDestination(
                                    item
                                )
                            )
                        }
                    )
                    if (i < state.materials.size) {
                        Divider(
                            modifier = Modifier.padding(
                                horizontal = 16.dp
                            )
                        )
                    }
                }
            }
        }
        else if(state.materials.isEmpty() && !state.isLoading && state.searchQuery.isNotEmpty()){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Ничего не найдено",
                    fontSize = 14.sp
                )
            }
        }
    }
}