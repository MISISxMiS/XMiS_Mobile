package com.example.xmis_project.ui.theme.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.misis_xmis.R

@Composable
fun WelcomeScreen(onNavigateToChat: () -> Unit) {
//    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(painter = painterResource(id = R.drawable.icon),
                contentDescription = "Логотип приложения",
                modifier = Modifier.fillMaxWidth().height(400.dp))

            Text(
                text = "Выбрать места на Китай-городе",
                fontSize = 28.sp,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            Text(
                text = "Какие планы на сегодня?"
            )

            Text(text = "Потусить в баре с друзьями...")

            Button(
                onClick = onNavigateToChat,
                contentPadding = PaddingValues(horizontal = 30.dp, vertical = 15.dp)
            ) {
                Text("Продолжить", fontSize = 18.sp)
            }

        }
//    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(onNavigateToChat = {})
}