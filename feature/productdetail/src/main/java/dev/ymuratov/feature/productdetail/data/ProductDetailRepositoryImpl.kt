package dev.ymuratov.feature.productdetail.data

import dev.ymuratov.core.models.ProductModel
import dev.ymuratov.core.network.api.ProductApiService
import dev.ymuratov.core.network.di.IoDispatcher
import dev.ymuratov.core.network.dto.toDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductDetailRepositoryImpl @Inject constructor(
    private val api: ProductApiService, @param:IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ProductDetailRepository {

    override fun getProduct(id: Int): Flow<ProductModel> = flow {
        emit(api.getProductById(id))
    }.map { product ->
        product.toDomain()
    }.flowOn(ioDispatcher)
}


