package dev.ymuratov.feature.categories.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.ymuratov.core.models.CategoryModel
import dev.ymuratov.core.ui.theme.AppTheme

@Composable
fun CategoryCard(category: CategoryModel, modifier: Modifier = Modifier, onCategoryClick: () -> Unit = {}) {
    val shape = RoundedCornerShape(12.dp)
    with(category) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(color = AppTheme.colors.backgroundSecondary, shape = shape)
                .clip(shape = shape)
                .clickable { onCategoryClick() }
                .padding(12.dp)) {
            Text(
                text = name.ifBlank { slug },
                color = AppTheme.colors.textPrimary,
                style = AppTheme.typography.textMedium,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}