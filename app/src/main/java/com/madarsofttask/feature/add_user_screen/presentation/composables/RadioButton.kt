package com.madarsofttask.feature.add_user_screen.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.madarsofttask.ui.theme.LightBlue
import com.madarsofttask.ui.theme.OffWhite

@Composable
fun RadioButton(
    text: String,
    selectedOption: String?,
    onOptionSelected: (String) -> Unit,
) {
    Row(
        modifier = Modifier.clickable { onOptionSelected(text) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selectedOption == text,
            onClick = { onOptionSelected(text) },
            colors = RadioButtonDefaults.colors(
                selectedColor = LightBlue, unselectedColor = OffWhite
            )
        )
        Text(
            text = text,
            fontSize = 16.sp,
            style = MaterialTheme.typography.titleMedium.copy(color = Color.Black)
        )
    }
}