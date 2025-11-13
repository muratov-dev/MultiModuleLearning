package dev.ymuratov.feature.productdetail.domain

import dev.ymuratov.core.models.ProductModel
import dev.ymuratov.feature.productdetail.data.ProductDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val repository: ProductDetailRepository
) {
    operator fun invoke(id: Int): Flow<ProductModel> = repository.getProduct(id)
}


