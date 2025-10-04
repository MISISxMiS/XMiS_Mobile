package com.example.xmis_project.ui.theme.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.misis_xmis.R
import com.example.xmis_project.ui.theme.components.EditText
import com.example.xmis_project.ui.theme.components.MessageItem
import kotlin.collections.reversed


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataScreen(viewModel: DataViewModel = viewModel()) {
    val messages by viewModel.messages.collectAsState()

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
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {

            items(messages.reversed()) { message ->
                MessageItem(message)
            }

            item {
                EditText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    previousData = "",
                    hint = "Куда бы сходить...",
                    onTextChanged = {

                    },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                /* TODO */
                            },
                            colors = IconButtonDefaults.iconButtonColors(
                                contentColor = Color(0xFF3FE277)
                            )
                        ) {
                            Icon(painter = painterResource(R.drawable.ic_send),
                                contentDescription = "Отправить",
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(shape = RoundedCornerShape(36.dp)),
                            )
                        }
                    }
                )
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun DataScreenPreview() {
    DataScreen(viewModel = viewModel())
}
