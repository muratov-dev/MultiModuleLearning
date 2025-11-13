package dev.ymuratov.feature.categories

import dev.ymuratov.feature.categories.domain.CategoriesRepository
import dev.ymuratov.feature.categories.data.CategoriesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CategoriesModule {
    @Binds
    @Singleton
    abstract fun bindCategoriesRepository(impl: CategoriesRepositoryImpl): CategoriesRepository
}


