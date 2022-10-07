package com.example.gosts_and_materials.presentation.search

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.gosts_and_materials.domain.model.gosts.GostItem
import com.example.gosts_and_materials.presentation.destinations.DirectionDestination
import com.example.gosts_and_materials.presentation.destinations.FirmnessScreenDestination
import com.example.gosts_and_materials.presentation.destinations.SearchMarkDestination
import com.example.gosts_and_materials.presentation.destinations.SearchMechPropertiesDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


val searchTypes = listOf<String>(
    "Поиск по марке материала",
    "Поиск по механическим свойствам материала",
    "Поиск по твердости материала"
)
var destination: DirectionDestination = SearchMarkDestination

@SuppressLint("UnrememberedMutableState")
@Composable
@Destination
fun SearchScreen(
    navigator: DestinationsNavigator
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(searchTypes) { item ->
            SearchCard(item = item, onButtonClicked = {
                if (it == "Поиск по марке материала") {
                    destination = SearchMarkDestination
                }
                else if (it == "Поиск по механическим свойствам материала"){
                    destination = SearchMechPropertiesDestination
                }
                else{
                    destination = FirmnessScreenDestination
                }
                navigator.navigate(destination)
            })

            if (item != searchTypes.last()) {
                Divider(
                    modifier = Modifier.padding(
                        horizontal = 16.dp
                    )
                )
            }
        }
    }
}


@Composable
fun SearchCard(
    item: String,
    onButtonClicked: ((item: String) -> Unit)
) {
    Row(
        content = {
            Text(
                text = item,
                modifier = Modifier.weight(1F),
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {
                onButtonClicked.invoke(item)
            })
            .padding(16.dp)
    )
}
