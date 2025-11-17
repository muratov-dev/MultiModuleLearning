package dev.ymuratov.core.models

data class ProductModel(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val tags: List<String>,
    val brand: String?,
    val weight: Int,
    val reviews: List<ReviewModel>,
    val images: List<String>,
    val thumbnail: String
)
