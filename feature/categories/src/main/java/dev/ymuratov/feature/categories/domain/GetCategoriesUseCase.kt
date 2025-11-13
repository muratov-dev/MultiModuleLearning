package dev.ymuratov.feature.categories.domain

import dev.ymuratov.core.models.Category
import dev.ymuratov.feature.categories.data.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: CategoriesRepository
) {
    operator fun invoke(): Flow<List<Category>> = repository.getCategories()
}


