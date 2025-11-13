package dev.ymuratov.core.network.dto

import dev.ymuratov.core.models.CategoryModel
import kotlinx.serialization.Serializable

@Serializable
data class CategoryItemDto(
    val slug: String?, val name: String?, val url: String?
)

fun CategoryItemDto.toDomain(): CategoryModel {
    return CategoryModel(slug = slug ?: "unknown", name = name ?: "", url = url ?: "")
}