package dev.ymuratov.feature.productdetail.data

import dev.ymuratov.core.models.ProductModel
import kotlinx.coroutines.flow.Flow

interface ProductDetailRepository {
    fun getProduct(id: Int): Flow<ProductModel>
}


