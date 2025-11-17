package dev.ymuratov.core.ui.theme

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val colors = AppColors.Light
    val typography = AppTypography()
    val defaultTextStyle = typography.textRegular

    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppTypography provides typography,
        LocalTextStyle provides defaultTextStyle,
    ) {
        MaterialTheme(
            colorScheme = lightColorScheme(
                onSurface = AppTheme.colors.textPrimary,
                surface = AppTheme.colors.backgroundSecondary,
                background = AppTheme.colors.backgroundPrimary,
                primary = AppTheme.colors.buttonPrimaryDefault,
                onPrimary = AppTheme.colors.buttonTextPrimary,
            ), typography = Typography(), content = content
        )
    }
}

object AppTheme {
    val colors: AppColors
        @Composable get() = LocalAppColors.current

    val typography: AppTypography
        @Composable get() = LocalAppTypography.current
}

