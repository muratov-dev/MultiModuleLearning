package dev.ymuratov.feature.productdetail.presentation.model

sealed interface ProductDetailEvent {

    data object OnDataRefresh: ProductDetailEvent
    data object OnNavigateUp: ProductDetailEvent
}