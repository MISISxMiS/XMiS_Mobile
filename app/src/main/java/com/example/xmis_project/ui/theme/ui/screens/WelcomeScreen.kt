package com.example.xmis_project.ui.theme.ui.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.misis_xmis.R
import com.example.xmis_project.ui.theme.components.EditText
import com.example.xmis_project.ui.theme.components.RoundedButton

@Composable
fun WelcomeScreen(onNavigateToChat: (String) -> Unit,
                  onNavigateToReview: () -> Unit,) {

    var userInput by remember { mutableStateOf("") }

    Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = "Логотип приложения",
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )

            Text(
                text = "Выбрать места на Китай-городе",
                fontSize = 32.sp,
                modifier = Modifier
                    .padding(bottom = 12.dp),
                fontFamily = FontFamily(Font(R.font.ultra_bold)),
                textAlign = TextAlign.Center
            )

            Text(
                text = "Какие планы на сегодня?",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(bottom = 8.dp),
                fontFamily = FontFamily(Font(R.font.light)),
                textAlign = TextAlign.Center
            )


            EditText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 12.dp),
                previousData = "",
                hint = "Потусить в баре с друзьями...",
                onTextChanged = { newText ->
                    userInput = newText
                }
            )

            RoundedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                content = {
                    Text(
                        text = "Продолжить?",
                        fontSize = 32.sp,
                        modifier = Modifier,
                        fontFamily = FontFamily(Font(R.font.bold)),
                        textAlign = TextAlign.Center,
                        color = androidx.compose.ui.graphics.Color.White
                    )
                },
                onClick = {
                    if (userInput.isNotBlank()) {
                        Log.d("msggg", userInput)
                        val destinationRoute = "chat/${userInput}"
                        onNavigateToChat(destinationRoute)
                    }
                }
            )

        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 24.dp),
            border = BorderStroke(width = 1.dp, color = Color(0xff19AA1E)),
            content = {
                Text(
                    text = "Оставить отзыв?",
                    fontSize = 16.sp,
                    modifier = Modifier,
                    fontFamily = FontFamily(Font(R.font.bold)),
                    textAlign = TextAlign.Center,
                    color = Color(0xff19AA1E)
                )
            },
            onClick = {
                    onNavigateToReview()
            }
        )
        }
//    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(onNavigateToChat = {}, onNavigateToReview = {})
}