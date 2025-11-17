package dev.ymuratov.feature.categoryproducts.presentation.model

import androidx.compose.runtime.Immutable
import dev.ymuratov.core.models.ProductModel

@Immutable
data class CategoryProductsState(
    val isLoading: Boolean = true,
    val products: List<ProductModel> = emptyList(),
    val errorMessage: String? = null,
    val selectedCategorySlug: String? = null,
    val selectedCategoryTitle: String? = null
)
