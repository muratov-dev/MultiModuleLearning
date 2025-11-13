package dev.ymuratov.feature.categoryproducts.domain

import dev.ymuratov.core.models.Product
import dev.ymuratov.feature.categoryproducts.data.CategoryProductsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsByCategoryUseCase @Inject constructor(
    private val repository: CategoryProductsRepository
) {
    operator fun invoke(category: String): Flow<List<Product>> = repository.getProducts(category)
}


