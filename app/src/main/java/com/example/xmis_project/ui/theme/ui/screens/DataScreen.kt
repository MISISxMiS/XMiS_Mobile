package com.example.xmis_project.ui.theme.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.misis_xmis.R


data class Message(val text: String, val isUser: Boolean)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataScreen(viewModel: DataViewModel = viewModel()) {
    val messages by viewModel.messages.collectAsState()



    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Вечер в баре", fontSize = 18.sp,
                textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()) })
        },
        bottomBar = {
            MessageInput()
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            reverseLayout = true,
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(messages.reversed()) { message ->
                MessageItem(message)
            }
        }
    }
}

@Composable
fun MessageItem(message: Message) {
    if (message.isUser) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Card(
                shape = RoundedCornerShape(topStart = 20.dp,
                    topEnd = 20.dp,
                    bottomStart = 20.dp,
                    bottomEnd = 0.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xff3FE277)
                ),
                modifier = Modifier.widthIn(max = 300.dp)
            ) {
                Text(
                    text = message.text,
                    color = Color.White,
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
        }
    } else {
        Column(modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(horizontal = 20.dp)
                .background(color = Color(0x3FE277).copy(alpha = 0.2f))
                .clip(RoundedCornerShape(24.dp))) {
            Image(painter = painterResource(R.drawable.fire_example),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Card(
                shape = RoundedCornerShape(topStart = 20.dp,
                    topEnd = 20.dp,
                    bottomStart = 20.dp,
                    bottomEnd = 0.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black
                ),
                modifier = Modifier.widthIn(max = 300.dp)
            ) {
                Text(
                    text = message.text,
                    color = Color.White,
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
        }
    }
}

@Composable
fun MessageInput() {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Напишите ваш запрос...") },
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedContainerColor = Color(0xFFF8F9FE),
//                unfocusedContainerColor = Color(0xFFF8F9FE)),

        )

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(onClick = { }) {
            Icon(painter = painterResource(R.drawable.input_logo),
                contentDescription = "Отправить",
                tint = Color.White,
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp)
                    .background(Color.Green)
                    .padding(vertical = 8.dp, horizontal = 4.dp)
                    .clip(shape = RoundedCornerShape(36.dp)),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DataScreenPreview() {
    DataScreen(viewModel = viewModel())
}