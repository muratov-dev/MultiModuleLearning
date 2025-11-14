package dev.ymuratov.feature.categories.presentation.model

import androidx.compose.runtime.Immutable
import dev.ymuratov.core.models.CategoryModel

@Immutable
data class CategoriesState(
    val isLoading: Boolean = true,
    val categories: List<CategoryModel> = emptyList(),
    val errorMessage: String? = null
)
