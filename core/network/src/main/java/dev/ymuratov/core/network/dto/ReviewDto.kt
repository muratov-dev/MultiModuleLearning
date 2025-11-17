package dev.ymuratov.core.network.dto

import dev.ymuratov.core.models.ReviewModel
import kotlinx.serialization.Serializable

@Serializable
data class ReviewDto(
    val comment: String, val date: String, val rating: Int, val reviewerEmail: String, val reviewerName: String
)

fun ReviewDto.toDomain(): ReviewModel {
    return ReviewModel(
        rating = rating, comment = comment, date = date, reviewerName = reviewerName, reviewerEmail = reviewerEmail
    )
}