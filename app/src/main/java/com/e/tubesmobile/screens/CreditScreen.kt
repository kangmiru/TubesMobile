package com.e.tubesmobile.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CreditScreen() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Kredit")
        Text(text = "Developer")
        Text(text = "CHYNTIA CITRA RACHMAWATI - 203040002")
        Text(text = "HAFADZ HAZMIRULLAH - 203040022 ")
        Text(text = "MOCHAMMAD NIZAR ALBAEHAQI - 203040035")
        Text(text = "AYU PUTRI DWI ANNISA - 203040166")
        Text(text = "HERYANI - 203040169")
    }
}