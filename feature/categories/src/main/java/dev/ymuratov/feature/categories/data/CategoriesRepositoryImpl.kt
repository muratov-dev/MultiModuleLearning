package dev.ymuratov.feature.categories.data

import dev.ymuratov.core.models.CategoryModel
import dev.ymuratov.core.network.api.ProductApiService
import dev.ymuratov.core.network.di.IoDispatcher
import dev.ymuratov.core.network.dto.toDomain
import dev.ymuratov.feature.categories.domain.CategoriesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val api: ProductApiService, @param:IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : CategoriesRepository {

    override fun getCategories(): Flow<List<CategoryModel>> = flow {
        val items = api.getCategories()
        emit(items.map { dto -> dto.toDomain() })
    }.flowOn(ioDispatcher)
}


