package dev.ymuratov.feature.categoryproducts.domain

import dev.ymuratov.core.models.ProductModel
import kotlinx.coroutines.flow.Flow

interface CategoryProductsRepository {
    fun getProducts(category: String): Flow<List<ProductModel>>
}