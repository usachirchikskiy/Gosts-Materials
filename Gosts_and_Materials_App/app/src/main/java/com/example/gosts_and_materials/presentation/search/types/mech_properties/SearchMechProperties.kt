package com.example.gosts_and_materials.presentation.search.types.mech_properties

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gosts_and_materials.presentation.destinations.ResultMechScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

private const val TAG = "SearchMechProperties"

@Composable
@Destination
fun SearchMechProperties(
    navigator: DestinationsNavigator,
    viewModel: SearchMechPropertyViewModel = hiltViewModel()
) {

    val state = viewModel.state


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = "Предел кратковременной прочности : ",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 14.sp

        )

        Spacer(modifier = Modifier.width(8.dp))
        TextFieldWithInputType(
            mereValue = "Мпа",
            input = state.sigma_b1,
            onInputChanged = {
                viewModel.changeSigmaB1(it)
            }
        )

        Spacer(modifier = Modifier.width(4.dp))
        TextFieldWithInputType(
            mereValue = "Мпа",
            input = state.sigma_b2,
            onInputChanged = {
                viewModel.changeSigmaB2(it)
            }
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            text = "Предел пропорциональности : ",
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.width(8.dp))
        TextFieldWithInputType(
            mereValue = "Мпа",
            input = state.sigma_02_1,
            onInputChanged = {
                viewModel.changeSigma_02_1(it)
            }
        )

        Spacer(modifier = Modifier.width(4.dp))
        TextFieldWithInputType(
            mereValue = "Мпа",
            input = state.sigma_02_2,
            onInputChanged = {
                viewModel.changeSigma_02_2(it)
            }
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Относительное удлинение при разрыве : ",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
        )

        Spacer(modifier = Modifier.width(8.dp))
        TextFieldWithInputType(
            mereValue = "%",
            input = state.delta_5_1,
            onInputChanged = {
                viewModel.changeSigmaDelta1(it)
            }
        )

        Spacer(modifier = Modifier.width(4.dp))
        TextFieldWithInputType(
            mereValue = "%",
            input = state.delta_5_2,
            onInputChanged = {
                viewModel.changeSigmaDelta2(it)
            }
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Относительное сужение : ",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
        )

        Spacer(modifier = Modifier.width(8.dp))
        TextFieldWithInputType(
            mereValue = "%",
            input = state.fi1,
            onInputChanged = {
                viewModel.changeSigmaF1(it)
            }
        )

        Spacer(modifier = Modifier.width(4.dp))
        TextFieldWithInputType(
            mereValue = "%",
            input = state.fi2,
            onInputChanged = {
                viewModel.changeSigmaF2(it)
            }
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Ударная вязкость : ",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
        )

        Spacer(modifier = Modifier.width(8.dp))
        TextFieldWithInputType(
            mereValue = "кДж",
            input = state.kcu1,
            onInputChanged = {
                viewModel.changeSigmaKsu1(it)
            }
        )

        Spacer(modifier = Modifier.width(4.dp))
        TextFieldWithInputType(
            mereValue = "кДж",
            input = state.kcu2,
            onInputChanged = {
                viewModel.changeSigmaKsu2(it)
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

@Composable
fun TextFieldWithInputType(
    mereValue: String,
    input: String,
    onInputChanged: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = input,
        label = { Text(text = "Введите значение ($mereValue)") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = { userText ->
            onInputChanged.invoke(userText)
        }
    )
}