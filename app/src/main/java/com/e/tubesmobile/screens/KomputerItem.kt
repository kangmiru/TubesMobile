package com.e.tubesmobile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.e.tubesmobile.R
import com.e.tubesmobile.model.Komputer
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.message
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import com.vanpra.composematerialdialogs.title

@Composable
fun KompterItem(item: Komputer, navController: NavHostController, onDelete: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val subMenus = listOf("Edit", "Delete")
    val confirmationDialogState = rememberMaterialDialogState()
    
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterVertically),
                painter = painterResource(
                    id = R.drawable.ic_launcher_laptop
                ),
                contentDescription = "gambar laptop"
            )
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Merk", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = item.merk, fontSize = 14.sp, fontWeight = FontWeight.Light)

                Text(text = "Jenis", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = item.jenis, fontSize = 14.sp, fontWeight = FontWeight.Light)

                Text(text = "Harga", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = "Rp. ${item.harga}", fontSize = 14.sp, fontWeight = FontWeight.Light)

                Text(text = "Dapat DiUpgrade", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = item.dapatDiupgarade.toString(), fontSize = 14.sp, fontWeight = FontWeight.Light)

                Text(text = "Spesifikasi", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = item.spesifikasi, fontSize = 14.sp, fontWeight = FontWeight.Light)

                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = {
                            navController.navigate("edit-pengelolaan-komputer/${item.id}")
                        },
                        colors = ButtonDefaults.buttonColors(Color.Yellow),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.padding(2.dp)
                    ) {
                        Text(
                            text = "Edit",
                            color = Color.Black,
                        )
                    }
                    Button(
                        onClick = {
                            confirmationDialogState.show()
                        },
                        colors = ButtonDefaults.buttonColors(Color.Red),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.padding(2.dp)
                    ) {
                        Text(
                            text = "Hapus",
                            color = Color.White,
                        )
                    }
                }
            }
        }
    }

    Divider(modifier = Modifier.fillMaxWidth())
    MaterialDialog(dialogState = confirmationDialogState,
        buttons = {
            positiveButton("Ya", onClick = {
                onDelete(item.id)
            })
            negativeButton("Tidak")
        }) {
        title(text = "Konfirmasi")
        message(text = "Apakah anda yakin ingin menghapus data?")
    }
}