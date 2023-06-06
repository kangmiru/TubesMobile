package com.e.tubesmobile.screens

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
import com.e.tubesmobile.model.JenisPeriferal
import com.e.tubesmobile.screens.periferal.PengelolaanPeriferalViewModel
import com.e.tubesmobile.ui.theme.Purple700
import com.e.tubesmobile.ui.theme.Teal200
import kotlinx.coroutines.launch

@Composable
fun FormPencatatanPeriferal (navController: NavHostController, id: String? = null, modifier: Modifier = Modifier){
    val viewModel = hiltViewModel<PengelolaanPeriferalViewModel>()
    val nama = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val harga = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val deskripsi = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val jenisPeriferal = remember {
        mutableStateOf(JenisPeriferal.Keyboard)
    }
    val isLoading = remember {
        mutableStateOf(false)
    }
    val buttonLabel = if(isLoading.value) "Mohon Tunggu..." else "Simpan"
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
    ) {
        OutlinedTextField(
            label = { Text(text = "Nama Periferal")},
            value = nama.value,
            onValueChange = {
                nama.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "Nama Periferal")}
        )

        OutlinedTextField(
            label = { Text(text = "Harga Periferal") },
            value = harga.value,
            onValueChange = {
                harga.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = { Text(text = "Harga Periferal") }
        )

        OutlinedTextField(
            label = { Text(text = "Deskripsi")},
            value = deskripsi.value,
            onValueChange = {
                deskripsi.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "Deskripsi")}
        )

        OutlinedTextField(
            label = { Text(text = "Jenis Periferal") },
            value = jenisPeriferal.value.toString(),
            onValueChange = {},
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            enabled = false
        )

        var expanded by remember { mutableStateOf(false) }
        val items = JenisPeriferal.values()

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        jenisPeriferal.value = item
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
                            viewModel.insert(
                                nama.value.text,
                                harga.value.text.toIntOrNull() ?: 0,
                                deskripsi.value.text,
                                jenisPeriferal = jenisPeriferal.value
                            )
                        }
                    }else{
                        scope.launch {
                            viewModel.update(
                                id,
                                nama.value.text,
                                harga.value.text.toIntOrNull() ?: 0,
                                deskripsi.value.text,
                                jenisPeriferal.value
                            )
                        }
                    }
                    navController.navigate("pengelolaan-periferal")
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
                    nama.value = TextFieldValue("")
                    harga.value = TextFieldValue("")
                    deskripsi.value = TextFieldValue("")
                    jenisPeriferal.value = JenisPeriferal.Keyboard
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
            viewModel.loaditem(id){periferal ->
                periferal?.let {
                    nama.value = TextFieldValue("")
                    harga.value = TextFieldValue("")
                    deskripsi.value = TextFieldValue("")
                    jenisPeriferal.value = JenisPeriferal.valueOf(periferal.jenisPeriferal)
                }
            }
        }
    }
}