package com.e.tubesmobile.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.e.tubesmobile.model.Periferal
import com.e.tubesmobile.screens.periferal.PengelolaanPeriferalViewModel
import kotlinx.coroutines.launch

@Composable
fun PengelolaanPeriferalScreen(
    navController: NavHostController,
    snackbarHostState:SnackbarHostState,
    modifier: Modifier = Modifier
){
    val viewModel = hiltViewModel<PengelolaanPeriferalViewModel>()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val items : List<Periferal> by viewModel.list.observeAsState(initial = listOf())

    Column(modifier = Modifier.fillMaxWidth()) {
        Button(onClick = {
            navController.navigate("tambah-pengelolaan-periferal")
        }) {
            Text(text = "Tambah")
        }

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = items, itemContent = { item ->
                PeriferalItem(item = item, navController = navController) {
                    scope.launch {
                        viewModel.delete(it)
                    }
                }
            })
        }
    }

    LaunchedEffect(scope) {
        viewModel.loadItems()
    }
    viewModel.success.observe(LocalLifecycleOwner.current) {
        if (it) {
            scope.launch {
                viewModel.loadItems()
            }
        }
    }
    viewModel.toast.observe(LocalLifecycleOwner.current) {
        scope.launch {
            snackbarHostState.showSnackbar(it, actionLabel = "Tutup", duration = SnackbarDuration.Long)
        }
    }
}