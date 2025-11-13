package dev.ymuratov.core.network.dto

import kotlinx.serialization.Serializable


@Serializable
data class ProductsDto(
    val products: List<ProductDto>?,
    val total: Int? = null,
    val skip: Int? = null,
    val limit: Int? = null
)


