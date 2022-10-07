package com.example.gosts_and_materials.presentation.item_gost_info.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gosts_and_materials.domain.model.gosts.GostItemInfo

@Composable
fun GostItemView(
    item: GostItemInfo,
    //startDownload: (File) -> Unit
) {
    //val clicked = remember { mutableStateOf(Color.Black) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = item.name,
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
/*
        Text(
            text = "Скачать",
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = {
                    val file = File(
                        id = item.id.toString(),
                        name = item.name,
                        url = item.downloadlink,
                        type = "PDF"
                    )
                  if (!file.isDownloading) {
                       if (file.downloadedUri.isNullOrEmpty()) {
                           clicked.value = Teal200
                           startDownload(file)
                      }
                    }
                }
                ),
            overflow = TextOverflow.Ellipsis,
            color = clicked.value
        )
    }
    */
    }
}