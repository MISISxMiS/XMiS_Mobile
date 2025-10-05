package com.example.xmis_project.ui.theme.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil3.compose.AsyncImage
import com.example.xmis_project.models.Place

@Composable
fun PlaceDetailDialog(place: Place, onDismissRequest: () -> Unit, onMapLinkClick: (String) -> Unit) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(shape = RoundedCornerShape(16.dp)) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Название места
                item {
                    Text(
                        text = place.title,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
                // Изображение места
                item {
                    AsyncImage(
                        model = "http://fr.gryaz-vpn.com:8001/photos/${place.photo}",
                        contentDescription = place.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
                // Категории (типы объектов)
                item {
                    if (place.entity_types.isNotEmpty()) {
                        DetailSection(title = "Категории", items = place.entity_types)
                    }
                }
                // Причины посещения (теги цели)
                item {
                    if (place.purpose_tags.isNotEmpty()) {
                        DetailSection(title = "Для чего подходит", items = place.purpose_tags)
                    }
                }
                // Ссылка "На карте 2ГИС"
                item {
                    if (place.url.isNotBlank()) {
                        TextButton(onClick = { onMapLinkClick(place.url) }) {
                            Text(text="На карте 2ГИС", color = Color(0xff19AA1E))
                        }
                    }
                }
            }
        }
    }
}

// Вспомогательный компонент для секций с тегами
@Composable
fun DetailSection(title: String, items: List<String>) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Normal
        )
        FlowRow(
            modifier = Modifier.padding(top = 4.dp)
        ) {
            items.forEach { tag ->
                Text(
                    text = tag,
                    color = Color(0xff19AA1E),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(end = 8.dp, bottom = 4.dp)
                        .background(
                            color = Color(0xffD9F9E4),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}