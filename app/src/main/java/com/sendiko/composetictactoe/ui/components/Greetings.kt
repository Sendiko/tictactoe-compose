package com.sendiko.composetictactoe.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun Greetings(
    greeting: String
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = greeting,
        style = TextStyle(
            textAlign = TextAlign.Start,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold
        )
    )
}