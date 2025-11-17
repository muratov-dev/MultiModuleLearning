package dev.ymuratov.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class AppColors(
    val backgroundPrimary: Color = Color.Unspecified,
    val backgroundSecondary: Color = Color.Unspecified,
    val backgroundTertiary: Color = Color.Unspecified,
    val buttonPrimaryDefault: Color = Color.Unspecified,
    val textPrimary: Color = Color.Unspecified,
    val textSecondary: Color = Color.Unspecified,
    val successPrimary: Color = Color.Unspecified,
    val errorPrimary: Color = Color.Unspecified,
) {

    companion object {
        @Stable
        val Light = AppColors(
            backgroundPrimary = Color(0xFFF7F9FC),
            backgroundSecondary = Color(0xFFFFFFFF),
            backgroundTertiary = Color(0xFFF0F2F5),
            buttonPrimaryDefault = Color(0xFF3A7AFE),
            textPrimary = Color(0xFF1A1A1A),
            textSecondary = Color(0xFF5A5F6A),
            successPrimary = Color(0xFF22C55E),
            errorPrimary = Color(0xFFEF4444),
        )
    }
}

internal val LocalAppColors = staticCompositionLocalOf { AppColors() }