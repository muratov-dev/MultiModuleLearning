package dev.ymuratov.feature.categories.data

import dev.ymuratov.core.models.Category
import dev.ymuratov.core.network.api.ProductApiService
import dev.ymuratov.core.network.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val api: ProductApiService,
    @param:IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : CategoriesRepository {
    override fun getCategories(): Flow<List<Category>> = flow {
        val items = api.getCategories()
        emit(items.map { dto ->
            Category(
                slug = dto.slug ?: "unknown",
                name = dto.name ?: "",
                url = dto.url ?: ""
            )
        })
    }.flowOn(ioDispatcher)
}


