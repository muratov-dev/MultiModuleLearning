package dev.ymuratov.core.navigation

import kotlinx.serialization.Serializable

sealed class AppDestination {

    @Serializable
    object Categories : AppDestination()

    @Serializable
    data class CategoryProducts(val categorySlug: String) : AppDestination()

    @Serializable
    data class ProductDetail(val productId: Int) : AppDestination()
}