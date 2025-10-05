package com.example.xmis_project.ui.theme.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.xmis_project.ui.theme.components.ThankDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReviewScreen(navController: NavController?) {
    var placeName by remember { mutableStateOf("") }
    var gisLink by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var showThankYouDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Добавить отзыв",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            )
        },
        bottomBar = {
            Button(
                onClick = { showThankYouDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 48.dp, horizontal = 16.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff19AA1E)),
                enabled = placeName.isNotBlank() && description.isNotBlank()
            ) {
                Text("Продолжить", color = Color.White)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Поле "Название места"
            OutlinedTextField(
                value = placeName,
                onValueChange = { placeName = it },
                label = { Text("Название места") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Поле "Ссылка на 2ГИС"
            OutlinedTextField(
                value = gisLink,
                onValueChange = { gisLink = it },
                label = { Text("Ссылка на 2ГИС") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Поле "Описание"
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Описание") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                singleLine = false,
                maxLines = 5
            )
        }
    }

    // Благодарственное диалоговое окно
    if (showThankYouDialog) {
        ThankDialog(navController = navController)
    }
}