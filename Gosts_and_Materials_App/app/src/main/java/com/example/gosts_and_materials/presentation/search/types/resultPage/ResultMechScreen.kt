package com.example.gosts_and_materials.presentation.search.types.resultPage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gosts_and_materials.presentation.destinations.SearchItemInfoScreenDestination
import com.example.gosts_and_materials.presentation.search.types.resultPage.composables.ExpandedSearchListItemView
import com.example.gosts_and_materials.presentation.search.types.resultPage.composables.SearchListItemView
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun ResultMechScreen(
    bodyString: String,
    navigator: DestinationsNavigator,
    viewModel: ResultMechViewModel = hiltViewModel()
) {
    val state = viewModel.state
    if (state.results.isNotEmpty()) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            itemsIndexed(items = state.results, itemContent = { index, item ->
                Column(content = {
                    ExpandedSearchListItemView(
                        item!!,
                        onButtonClicked = { expanded: Boolean ->
                            viewModel.changeItemValue(
                                index,
                                expanded = expanded
                            )
                        })

                    if (item.isExpanded) {
                        SearchListItemView(items = item.items,
                            onButtonClicked = { item: String ->
                                navigator.navigate(
                                    SearchItemInfoScreenDestination(
                                        item
                                    )
                                )
                            }
                        )
                    }

                    if (item != state.results.last()) {
                        Divider(
                            modifier = Modifier.padding(
                                horizontal = 16.dp
                            )
                        )
                    }
                })
            })
        }
    } else if(state.results.isEmpty() && !state.isLoading){
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