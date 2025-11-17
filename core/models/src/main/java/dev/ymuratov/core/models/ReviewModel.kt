package dev.ymuratov.core.models

data class ReviewModel(
    val rating: Int,
    val comment: String,
    val date: String,
    val reviewerName: String,
    val reviewerEmail: String
)