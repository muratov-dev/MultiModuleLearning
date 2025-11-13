package dev.ymuratov.core.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class CategoryItemDto(
    val slug: String?,
    val name: String?,
    val url: String?
)
