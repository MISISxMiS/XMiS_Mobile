package com.example.xmis_project.ui.theme.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.misis_xmis.R
import com.example.xmis_project.models.Message

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
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp,
                    bottomStart = 20.dp,
                    bottomEnd = 0.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xff3FE277)
                ),
                modifier = Modifier
                    .widthIn(max = 300.dp)
            ) {
                Text(
                    text = message.text,
                    color = Color.White,
                    modifier = Modifier
                        .padding(12.dp),
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.light))
                )
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(start = 8.dp)
                .clip(shape = RoundedCornerShape(20.dp)),
        ) {
            Image(
                painter = painterResource(R.drawable.icon),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.4f),
                                Color.Black.copy(alpha = 0.6f)
                            ),
                            startY = 0f,
                            endY = Float.POSITIVE_INFINITY
                        ),
                    )
                    .padding(start = 8.dp, bottom = 8.dp)
                    .align(Alignment.BottomStart)
            ) {
                Text(
                    text = "Заголовок",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.bold))
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Плюшки",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.light))
                )
            }
        }
    }
}