package dev.ymuratov.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.ymuratov.core.ui.R
import dev.ymuratov.core.ui.theme.AppTheme

@Composable
fun ErrorView(message: String, modifier: Modifier = Modifier, onRetry: () -> Unit = {}) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = message, color = AppTheme.colors.textSecondary, style = AppTheme.typography.textRegular)
        Button(onClick = onRetry) {
            Text(
                stringResource(R.string.retry_button),
                color = AppTheme.colors.buttonTextPrimary,
                style = AppTheme.typography.buttonMedium
            )
        }
    }
}


