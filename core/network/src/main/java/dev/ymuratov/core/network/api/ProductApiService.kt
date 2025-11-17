package dev.ymuratov.core.network.api

import dev.ymuratov.core.network.dto.CategoryDto
import dev.ymuratov.core.network.dto.ProductDto
import dev.ymuratov.core.network.dto.ProductsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiService {
    @GET("products/categories")
    suspend fun getCategories(): List<CategoryDto>

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(
        @Path("category") category: String
    ): ProductsDto

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ): ProductDto
}


