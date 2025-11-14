package dev.ymuratov.feature.productdetail.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ymuratov.feature.productdetail.data.ProductDetailRepository
import dev.ymuratov.feature.productdetail.data.ProductDetailRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ProductDetailModule {
    @Binds
    @Singleton
    abstract fun bindProductDetailRepository(impl: ProductDetailRepositoryImpl): ProductDetailRepository
}