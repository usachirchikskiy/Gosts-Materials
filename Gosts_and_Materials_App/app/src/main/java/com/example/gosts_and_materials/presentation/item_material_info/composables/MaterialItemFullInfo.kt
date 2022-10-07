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
import com.example.gosts_and_materials.domain.model.materials.MaterialItemInfo
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun MaterialItemFullInfo(
    item: MaterialItemInfo
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
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

        Text(
            text = "Xимический состав в % материала",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        val splittedText = item.chemical.split("\n")
        var result = ""
        for(item in splittedText){
            var resText = item
            val jItem  = item.split(" ")
            try {
                if("до" !in jItem){
                    resText = jItem[0] + " " + jItem[1] + " - " + jItem[2]
                }
            }catch (ex:Exception){
                print("Error")
            }

            result += resText + "\n"
        }
        Text(
            text = result,
            fontSize = 14.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

    }
}