package dev.ymuratov.core.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.ymuratov.core.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(title: String, modifier: Modifier = Modifier, navigationIcon: (@Composable () -> Unit)? = null) {
    CenterAlignedTopAppBar(
        modifier = modifier.dropShadow(shape = RoundedCornerShape(4.dp)) {
            radius = 10f
            color = Color(0xFFF0F2F5)
            offset = Offset(0f, 4f)
        },
        title = { Text(text = title, color = AppTheme.colors.textPrimary, style = AppTheme.typography.h2) },
        navigationIcon = { navigationIcon?.let { navigationIcon() } })
}


