package dev.ymuratov.feature.categories.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.ymuratov.core.models.CategoryModel

@Composable
fun CategoryCard(category: CategoryModel, modifier: Modifier = Modifier, onCategoryClick: (String) -> Unit = {}) {
    Card(modifier = modifier
        .fillMaxWidth()
        .clickable { onCategoryClick(category.slug) }
        .padding(12.dp)) {
        Text(text = category.name.ifBlank { category.slug }, modifier = Modifier.fillMaxWidth(), color = Color.Black)
    }
}