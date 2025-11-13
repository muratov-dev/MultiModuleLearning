package dev.ymuratov.feature.categoryproducts.data

import dev.ymuratov.core.models.Product
import kotlinx.coroutines.flow.Flow

interface CategoryProductsRepository {
    fun getProducts(category: String): Flow<List<Product>>
}


