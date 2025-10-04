package com.example.xmis_project.ui.theme.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun WelcomeScreen(onNavigateToChat: () -> Unit) {
//    Surface(modifier = Modifier.fillMaxSize()) {
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
                onTextChanged = {

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
                    onNavigateToChat()
                }
            )
        }
//    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(onNavigateToChat = {})
}