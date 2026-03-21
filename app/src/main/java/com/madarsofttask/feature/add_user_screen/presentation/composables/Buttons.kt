package com.madarsofttask.feature.add_user_screen.presentation.composables

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.madarsofttask.ui.theme.DarkBlue
import com.madarsofttask.ui.theme.Orange
import com.madarsofttask.ui.theme.White

@Composable
fun SubmitButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = 45.dp),
        enabled = isEnabled,
        content = content,
        colors = submitButtonColors(),
        shape = shape,
    )
}


@Composable
fun NextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = 45.dp),
        enabled = isEnabled,
        content = content,
        colors = nextButtonColors(),
        shape = shape,
    )
}

@Composable
fun submitButtonColors() = ButtonDefaults.buttonColors(
    containerColor = Orange,
    contentColor = White,
)

@Composable
fun nextButtonColors() = ButtonDefaults.buttonColors(
    containerColor = DarkBlue,
    contentColor = White,
)