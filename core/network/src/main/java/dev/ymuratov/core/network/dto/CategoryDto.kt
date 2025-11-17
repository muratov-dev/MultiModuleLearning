package dev.ymuratov.core.network.dto

import dev.ymuratov.core.models.CategoryModel
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    val slug: String?, val name: String?, val url: String?
)

fun CategoryDto.toDomain(): CategoryModel {
    return CategoryModel(slug = slug ?: "unknown", name = name ?: "", url = url ?: "")
}