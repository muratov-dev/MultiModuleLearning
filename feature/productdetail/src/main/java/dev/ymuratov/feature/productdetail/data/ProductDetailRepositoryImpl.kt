package dev.ymuratov.feature.productdetail.data

import dev.ymuratov.core.models.ProductModel
import dev.ymuratov.core.network.api.ProductApiService
import dev.ymuratov.core.network.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductDetailRepositoryImpl @Inject constructor(
    private val api: ProductApiService, @param:IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ProductDetailRepository {
    override fun getProduct(id: Int): Flow<ProductModel> = flow {
        val dto = api.getProductById(id)
        emit(
            ProductModel(
                id = dto.id ?: -1,
                title = dto.title ?: "",
                description = dto.description ?: "",
                price = dto.price ?: 0.0,
                discountPercentage = dto.discountPercentage ?: 0.0,
                rating = dto.rating ?: 0.0,
                stock = dto.stock ?: 0,
                brand = dto.brand ?: "",
                category = dto.category ?: "",
                thumbnail = dto.thumbnail ?: "",
                images = dto.images ?: emptyList()
            )
        )
    }.flowOn(ioDispatcher)
}


