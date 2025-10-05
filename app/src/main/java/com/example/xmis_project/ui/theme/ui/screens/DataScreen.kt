package com.example.xmis_project.ui.theme.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.misis_xmis.R
import com.example.xmis_project.models.Message
import com.example.xmis_project.models.Place
import com.example.xmis_project.ui.theme.components.EditText
import com.example.xmis_project.ui.theme.components.PlaceDetailDialog
import kotlinx.coroutines.delay

//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DataScreen(viewModel: DataViewModel = viewModel()) {
//    var selectedPlace by remember { mutableStateOf<Place?>(null) }
//
//    // Обработчик для открытия ссылки
//    val context = LocalContext.current
//    val handleMapLinkClick = { url: String ->
//        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//        ContextCompat.startActivity(context, intent, null)
//    }
//
//    val messages by viewModel.messages.collectAsState()
//    val isLoading by viewModel.isLoading.collectAsState()
//    val listState = rememberLazyListState()
//
//    LaunchedEffect(messages) {
//        if (messages.isNotEmpty()) {
//            listState.animateScrollToItem(0)
//        }
//    }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(
//                        text = "Вечер в баре",
//                        fontSize = 24.sp,
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier.fillMaxWidth(),
//                        fontFamily = FontFamily(Font(R.font.ultra_bold))
//                    )
//                }
//            )
//        }
//    ) { paddingValues ->
//        Box(modifier = Modifier.fillMaxSize()) {
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(paddingValues),
//                reverseLayout = true,
//                contentPadding = PaddingValues(vertical = 8.dp)
//            ) {
//                item {
//                    MessageInput(
//                        onSendMessage = { text ->
//                            if (text.isNotBlank()) {
//                                viewModel.sendUserPrompt(text)
//                            }
//                        },
//                        isLoading = isLoading
//                    )
//                }
//
//                items(messages.reversed()) { message ->
//                    MessageItem(message)
//                }
//            }
//
//            // Индикатор загрузки
//            if (isLoading) {
//                CircularProgressIndicator(
//                    modifier = Modifier
//                        .align(Alignment.Center)
//                        .padding(16.dp)
//                )
//            }
//        }
//    }
//
//}
//
@Composable
fun MessageInput(
    onSendMessage: (String) -> Unit,
    isLoading: Boolean = false
) {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Напишите ваш запрос...") },
            modifier = Modifier.weight(1f),
            enabled = !isLoading
        )

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(
            onClick = {
                if (text.isNotBlank() && !isLoading) {
                    onSendMessage(text)
                    text = ""
                }
            },
            enabled = text.isNotBlank() && !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    strokeWidth = 2.dp
                )
            } else {
                Icon(
                    painter = painterResource(R.drawable.input_logo),
                    contentDescription = "Отправить",
                    tint = Color.White,
                    modifier = Modifier
                        .width(64.dp)
                        .height(64.dp)
                        .background(Color(0xff19AA1E))
                        .padding(vertical = 8.dp, horizontal = 4.dp)
                        .clip(shape = RoundedCornerShape(36.dp))
                )
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
                    containerColor = Color(0xff19AA1E)
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
            .padding(20.dp)
            .background(color = Color(0x3FE277).copy(alpha = 0.2f))
            .clip(RoundedCornerShape(24.dp))) {
            Image(painter = painterResource(R.drawable.fire_example),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(vertical=30.dp)
                    .clip(RoundedCornerShape(16.dp)))
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataScreen(viewModel: DataViewModel = viewModel()) {
    var selectedPlace by remember { mutableStateOf<Place?>(null) }

    // Обработчик для открытия ссылки
    val context = LocalContext.current
    val handleMapLinkClick = { url: String ->
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        ContextCompat.startActivity(context, intent, null)
    }

    val messages by viewModel.messages.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val listState = rememberLazyListState()

    LaunchedEffect(messages) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(0)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Вечер в баре",
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        fontFamily = FontFamily(Font(R.font.ultra_bold))
                    )
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                reverseLayout = true,
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                item {
                    MessageInput(
                        onSendMessage = { text ->
                            if (text.isNotBlank()) {
                                viewModel.sendUserPrompt(text)
                            }
                        },
                        isLoading = isLoading
                    )
                }

                items(messages.reversed()) { message ->
                    if (message.isUser) {
                        UserMessageItem(message)
                    } else {
                        // Для сообщений с рекомендациями
                        if (message.places != null && message.places.isNotEmpty()) {
                            RecommendationMessageItem(
                                message = message,
                                onPlaceClick = { place -> selectedPlace = place }
                            )
                        } else {
                            BotMessageItem(message)
                        }
                    }
                }
            }

            // Индикатор загрузки
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                )
            }

            // Диалоговое окно с деталями места
            selectedPlace?.let { place ->
                PlaceDetailDialog(
                    place = place,
                    onDismissRequest = { selectedPlace = null },
                    onMapLinkClick = handleMapLinkClick
                )
            }
        }
    }
}

@Composable
fun UserMessageItem(message: Message) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Card(
            shape = RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp,
                bottomStart = 20.dp,
                bottomEnd = 0.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xff19AA1E)
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

@Composable
fun BotMessageItem(message: Message) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .background(color = Color(0x3FE277).copy(alpha = 0.2f))
            .clip(RoundedCornerShape(24.dp))
    ) {
        Text(
            text = message.text,
            modifier = Modifier.padding(16.dp),
            color = Color.Black
        )
    }
}

@Composable
fun RecommendationMessageItem(message: Message, onPlaceClick: (Place) -> Unit) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .background(color = Color(0x3FE277).copy(alpha = 0.2f))
            .clip(RoundedCornerShape(24.dp))
    ) {
        // Текст сообщения бота
        if (message.text.isNotBlank()) {
            Text(
                text = message.text,
                modifier = Modifier.padding(16.dp),
                color = Color.Black
            )
        }

        // Карточки мест
        message.places?.forEach { place ->
            PlaceCard(
                place = place,
                onItemClick = onPlaceClick,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}


@Composable
fun PlaceCard(place: Place, onItemClick: (Place) -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick(place) },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // Изображение места
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.LightGray)
            ) {
                // Здесь можно использовать AsyncImage для загрузки реального изображения
                Image(
                    painter = painterResource(R.drawable.fire_example), // временное изображение
                    contentDescription = place.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                )

                // Градиент поверх изображения для лучшей читаемости текста
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.3f)),
                                startY = 0f,
                                endY = 200f
                            )
                        )
                )

                // Название места поверх изображения
                Text(
                    text = place.title,
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                )
            }

            // Описание места
            Text(
                text = place.description,
                modifier = Modifier.padding(16.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            // Кнопка "Написать отзыв"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Button(
                    onClick = { onItemClick(place) },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff19AA1E)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Написать отзыв",
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DataScreenPreview() {
    DataScreen(viewModel = viewModel())
}