package com.e.tubesmobile.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.e.tubesmobile.model.JenisSmarthphone
import com.e.tubesmobile.ui.theme.Purple700
import com.e.tubesmobile.ui.theme.Teal200
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun FormPencatatanSmarthphone (navController: NavHostController, id: String? = null, modifier: Modifier = Modifier){
    val viewModel = hiltViewModel<PengelolaanSmarthphoneViewModel>()
    val model = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val warna = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val storage = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val tanggalRilis = remember { mutableStateOf<Date?>(null) }
    val sistemOperasi = remember {
        mutableStateOf(JenisSmarthphone.Android)
    }
    val isLoading = remember {
        mutableStateOf(false)
    }
    val buttonLabel = if (isLoading.value) "Mohon Tunggu..." else "Simpan"
    val scope = rememberCoroutineScope()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val tanggalDialogState = rememberMaterialDialogState()

    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
    ) {
        OutlinedTextField(
            label = { Text(text = "Model Smarthphone") },
            value = model.value,
            onValueChange = {
                model.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "Model Smarthphone") }
        )

        OutlinedTextField(
            label = { Text(text = "Warna Smarthphone") },
            value = warna.value,
            onValueChange = {
                warna.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "Warna Smarthphone") }
        )

        OutlinedTextField(
            label = { Text(text = "Storage Smarthphone") },
            value = storage.value,
            onValueChange = {
                storage.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = { Text(text = "Storage Periferal") }
        )

        OutlinedTextField(
            label = { Text(text = "Tanggal Rilis") },
            value = tanggalRilis.value?.let { dateFormat.format(it) } ?: "",
            onValueChange = {},
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .clickable {
                    tanggalDialogState.show()
                },
            placeholder = { Text(text = "yyyy-mm-dd") },
            enabled = false
        )

        OutlinedTextField(
            label = { Text(text = "Jenis Operasi Sistem Smarthphone") },
            value = sistemOperasi.value.toString(),
            onValueChange = {},
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            enabled = false
        )

        var expanded by remember { mutableStateOf(false) }
        val items = JenisSmarthphone.values()

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        sistemOperasi.value = item
                        expanded = false
                    }
                ) {
                    Text(text = item.toString())
                }
            }
        }

        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Open Dropdown")
        }

        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )

        Row(modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
        ){
            Button(
                modifier = Modifier.weight(5f),
                onClick = {
                    if (id == null){
                        scope.launch{
                            val formattedDate = dateFormat.format(tanggalRilis.value ?: Date())
                            viewModel.insert(
                                model.value.text,
                                warna.value.text,
                                storage.value.text.toIntOrNull() ?: 0,
                                formattedDate,
                                sistemOperasi.value
                            )
                        }
                    }else{
                        scope.launch{
                            val formattedDate = dateFormat.format(tanggalRilis.value ?: Date())
                            viewModel.update(
                                id,
                                model.value.text,
                                warna.value.text,
                                storage.value.text.toIntOrNull() ?: 0,
                                formattedDate,
                                sistemOperasi.value
                            )
                        }
                    }
                    navController.navigate("pengelolaan-smarthphone")
                },
                colors = loginButtonColors
            ) {
                Text(
                    text = buttonLabel,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.padding(8.dp)
                )
            }

            Button(
                modifier = Modifier.weight(5f),
                onClick = {
                    model.value = TextFieldValue("")
                    warna.value = TextFieldValue("")
                    storage.value = TextFieldValue("")
                    tanggalRilis.value = null
                    sistemOperasi.value = JenisSmarthphone.Android
                },
                colors = resetButtonColors
            ) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }

    viewModel.isLoading.observe(LocalLifecycleOwner.current){
        isLoading.value = it
    }

    if (id != null){
        LaunchedEffect(key1 = id){
            viewModel.loadItem(id){smarthphone ->
                smarthphone?.let {
                    model.value = TextFieldValue("")
                    warna.value = TextFieldValue("")
                    storage.value = TextFieldValue("")
                    tanggalRilis.value = null
                    sistemOperasi.value = JenisSmarthphone.valueOf(smarthphone.sistemOperasi)
                }
            }
        }
    }
}