package dev.ymuratov.core.network.dto

import dev.ymuratov.core.models.ProductModel
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val availabilityStatus: String,
    val brand: String? = null,
    val category: String,
    val description: String,
    val discountPercentage: Double,
    val id: Int,
    val images: List<String>,
    val minimumOrderQuantity: Int,
    val price: Double,
    val rating: Double,
    val returnPolicy: String,
    val reviews: List<ReviewDto>,
    val shippingInformation: String,
    val sku: String,
    val stock: Int,
    val tags: List<String>,
    val thumbnail: String,
    val title: String,
    val warrantyInformation: String,
    val weight: Int
)

fun ProductDto.toDomain(): ProductModel {
    return ProductModel(
        id = id,
        title = title,
        description = description,
        category = category,
        price = price,
        discountPercentage = discountPercentage,
        rating = rating,
        stock = stock,
        tags = tags,
        brand = brand,
        weight = weight,
        reviews = reviews.map { it.toDomain() },
        images = images,
        thumbnail = thumbnail
    )
}