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
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.e.tubesmobile.model.JenisKomputer
import com.e.tubesmobile.ui.theme.Purple700
import com.e.tubesmobile.ui.theme.Teal200
import kotlinx.coroutines.launch

@Composable
fun FormPencatatanKomputerScreen(navController: NavHostController, id: String? = null, modifier: Modifier = Modifier){
    val isLoading = remember { mutableStateOf(false) }
    val buttonLabel = if (isLoading.value) "Mohon tunggu..." else
        "Simpan"

    val viewModel = hiltViewModel<PengelolaanKomputerViewModel>()

    val merk = remember { mutableStateOf(TextFieldValue("")) }
    var jenis by remember { mutableStateOf(JenisKomputer.Laptop) }
    val harga = remember { mutableStateOf(TextFieldValue("")) }
    val dapatDiupgrade = remember { mutableStateOf(TextFieldValue("")) }
    val spesifikasi = remember { mutableStateOf(TextFieldValue("")) }

    val scope = rememberCoroutineScope()

    Column(modifier = modifier
        .fillMaxWidth()) {

        OutlinedTextField(
            label = { Text(text = "Merk") },
            value = merk.value,
            onValueChange = {
                merk.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "XXXXX") }
        )

        OutlinedTextField(
            label = { Text(text = "Jenis") },
            value = jenis.toString(),
            onValueChange = { },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            enabled = false
        )
        var expanded by remember {
            mutableStateOf(false)
        }

        val items = JenisKomputer.values()

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        jenis = item
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

        OutlinedTextField(
            label = { Text(text = "Harga") },
            value = harga.value,
            onValueChange = {
                harga.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Decimal),
            placeholder = { Text(text = "5") }
        )

        OutlinedTextField(
            label = { Text(text = "Dapat Di Upgrade") },
            value = dapatDiupgrade.value,
            onValueChange = {
                dapatDiupgrade.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Decimal),
            placeholder = { Text(text = "5") }
        )

        OutlinedTextField(
            label = { Text(text = "Harga") },
            value = spesifikasi.value,
            onValueChange = {
                spesifikasi.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "XXXXX") }
        )

        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )

        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )

        Row (modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {
                if (id == null){
                    scope.launch {
                        viewModel.insert(merk.value.text, jenis.toString(),
                            harga.value.text.toInt(), dapatDiupgrade.value.text.toBoolean(), spesifikasi.value.text)
                    }
                } else {
                    scope.launch {
                        viewModel.update(id, merk.value.text, jenis.toString(),
                            harga.value.text.toInt(), dapatDiupgrade.value.text.toBoolean(), spesifikasi.value.text)
                    }
                }
                navController.navigate("pengelolaan-komputer")
            }, colors = loginButtonColors) {
                Text(
                    text = buttonLabel,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }

            Button(modifier = Modifier.weight(5f), onClick = {
                merk.value = TextFieldValue("")
                jenis.value = JenisKomputer.values()
                harga.value = TextFieldValue("")
                dapatDiupgrade.value = TextFieldValue("")
                spesifikasi.value = TextFieldValue("")
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }

    viewModel.isLoading.observe(LocalLifecycleOwner.current) {
        isLoading.value = it
    }

    if (id != null) {
        LaunchedEffect(scope){
            viewModel.loadItem(id) {
                    komputer -> komputer?.let {
                merk.value = TextFieldValue(komputer.merk)
                jenis.value = TextFieldValue(komputer.jenis)
                harga.value = TextFieldValue(komputer.harga)
                dapatDiupgrade.value = TextFieldValue(komputer.dapatDiupgarade)
                spesifikasi.value = TextFieldValue(komputer.spesifikasi)
            }
            }
        }
    }
}