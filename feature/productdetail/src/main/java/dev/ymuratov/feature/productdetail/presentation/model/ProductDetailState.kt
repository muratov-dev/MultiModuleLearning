package dev.ymuratov.feature.productdetail.presentation.model

import androidx.compose.runtime.Immutable
import dev.ymuratov.core.models.ProductModel

@Immutable
data class ProductDetailState(
    val isLoading: Boolean = true,
    val productInfo: ProductModel? = null,
    val errorMessage: String? = null
)