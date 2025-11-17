package dev.ymuratov.feature.categoryproducts.data

import dev.ymuratov.core.models.ProductModel
import dev.ymuratov.core.network.api.ProductApiService
import dev.ymuratov.core.network.di.IoDispatcher
import dev.ymuratov.core.network.dto.toDomain
import dev.ymuratov.feature.categoryproducts.domain.CategoryProductsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryProductsRepositoryImpl @Inject constructor(
    private val api: ProductApiService, @param:IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : CategoryProductsRepository {

    override fun getProducts(category: String): Flow<List<ProductModel>> = flow {
        emit(api.getProductsByCategory(category))
    }.map { products ->
        products.products?.map { it.toDomain() } ?: emptyList()
    }.flowOn(ioDispatcher)
}


