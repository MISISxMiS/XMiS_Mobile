package com.example.xmis_project.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.misis_xmis.R

@Composable
fun EditText (
    modifier: Modifier,
    previousData: String,
    hint: String,
    trailingIcon: @Composable (() -> Unit)? = null,
    onTextChanged: (String) -> Unit,
) {

    var textValue by remember {
        mutableStateOf(previousData)
    }

    TextField(
        value = textValue,
        onValueChange = {
            textValue = it
            onTextChanged(it)
        },
        placeholder = {
            Text(
                text = hint,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.light)),
                color = Color(0xFF3FE277),
            )
        },
        shape = RoundedCornerShape(20.dp),
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .border(
                width = 2.dp,
                color = Color(0xFF3FE277),
                shape = RoundedCornerShape(20.dp)
            )
            .background(Color.Transparent),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color(0xFF3FE277),
            unfocusedTextColor = Color(0xFF3FE277),
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,

        ),
        trailingIcon = trailingIcon
    )
}
