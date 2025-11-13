package dev.ymuratov.feature.categories.domain

import dev.ymuratov.core.models.CategoryModel
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    fun getCategories(): Flow<List<CategoryModel>>
}