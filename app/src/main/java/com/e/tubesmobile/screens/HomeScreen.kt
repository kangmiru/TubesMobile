package com.e.tubesmobile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.e.tubesmobile.R

@Composable
fun HomeScreen() {
    val menus = listOf(
        Menu.HOME,
        Menu.PENGELOLAAN_KOMPUTER,
        Menu.SETTING)
    val listState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(modifier = Modifier
            .size(200.dp),
            painter = painterResource(
                id = R.drawable.ic_launcher_logo),
            contentDescription = "Halaman Home"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Selamat Datang",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier.padding(end = 78.dp, start = 78.dp),
            text = "Cari Komputer, Periferal, dan Smarthphone Disini",
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .height(45.dp)
                .width(200.dp),
            colors = ButtonDefaults.buttonColors(Color.Green),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Komputer",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .height(45.dp)
                .width(200.dp),
            colors = ButtonDefaults.buttonColors(Color.Green),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Periferal",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .height(45.dp)
                .width(200.dp),
            colors = ButtonDefaults.buttonColors(Color.Green),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Smarthphone",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
    }
}