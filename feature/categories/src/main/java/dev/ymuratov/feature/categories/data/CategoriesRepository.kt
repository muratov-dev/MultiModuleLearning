package dev.ymuratov.feature.categories.data

import dev.ymuratov.core.models.Category
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    fun getCategories(): Flow<List<Category>>
}


