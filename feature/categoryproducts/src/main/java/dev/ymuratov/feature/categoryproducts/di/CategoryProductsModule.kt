package dev.ymuratov.feature.categoryproducts.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ymuratov.feature.categoryproducts.domain.CategoryProductsRepository
import dev.ymuratov.feature.categoryproducts.data.CategoryProductsRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CategoryProductsModule {
    @Binds
    @Singleton
    abstract fun bindCategoryProductsRepository(impl: CategoryProductsRepositoryImpl): CategoryProductsRepository
}