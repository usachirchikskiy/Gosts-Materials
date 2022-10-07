package com.example.gosts_and_materials.presentation.search.types.firmness

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gosts_and_materials.presentation.destinations.ResultMechScreenDestination
import com.example.gosts_and_materials.presentation.search.types.mech_properties.TextFieldWithInputType
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun FirmnessScreen(
    navigator: DestinationsNavigator,
    viewModel: FirmnessViewModel = hiltViewModel()
    ) {

        val state = viewModel.state


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp, vertical = 16.dp)
        ) {

            Text(
                text = "Твердость по Бринеллю : ",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 14.sp

            )

            Spacer(modifier = Modifier.width(8.dp))
            TextFieldWithInputType(
                mereValue = "HB 10^(-1) МПа",
                input = state.hb1,
                onInputChanged = {
                    viewModel.changeHB1(it)
                }
            )

            Spacer(modifier = Modifier.width(4.dp))
            TextFieldWithInputType(
                mereValue = "HB 10^(-1) МПа",
                input = state.hb2,
                onInputChanged = {
                    viewModel.changeHB2(it)
                }
            )

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                modifier = Modifier
                    .align(Alignment.End)
                    .fillMaxWidth(),
                onClick = {
                    navigator.navigate(ResultMechScreenDestination(viewModel.makeBody()))
                }
            ) {
                Text(text = "Поиск")
            }

        }
    }