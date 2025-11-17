package dev.ymuratov.feature.productdetail.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ymuratov.core.navigation.AppDestination
import dev.ymuratov.core.ui.viewmodel.BaseViewModel
import dev.ymuratov.feature.productdetail.data.ProductDetailRepository
import dev.ymuratov.feature.productdetail.presentation.model.ProductDetailAction
import dev.ymuratov.feature.productdetail.presentation.model.ProductDetailEvent
import dev.ymuratov.feature.productdetail.presentation.model.ProductDetailState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle, private val repository: ProductDetailRepository
) : BaseViewModel<ProductDetailState, ProductDetailEvent, ProductDetailAction>(ProductDetailState()) {

    private val args = savedStateHandle.toRoute<AppDestination.ProductDetail>()

    override fun obtainEvent(viewEvent: ProductDetailEvent) {
        when (viewEvent) {
            ProductDetailEvent.OnNavigateUp -> sendAction(ProductDetailAction.NavigateUp)
            ProductDetailEvent.OnDataRefresh -> refreshData()
        }
    }

    private fun refreshData() {
        repository.getProduct(args.productId).onStart {
            updateViewState {
                copy(isLoading = true, errorMessage = null)
            }
        }.onEach { info ->
            updateViewState { copy(productInfo = info, isLoading = false) }
        }.catch { t ->
            updateViewState {
                copy(errorMessage = t.message ?: "Unknown error", isLoading = false)
            }
        }.launchIn(viewModelScope)
    }
}


