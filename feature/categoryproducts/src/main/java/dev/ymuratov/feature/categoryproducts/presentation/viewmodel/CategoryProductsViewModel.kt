package dev.ymuratov.feature.categoryproducts.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ymuratov.core.navigation.AppDestination
import dev.ymuratov.core.ui.viewmodel.BaseViewModel
import dev.ymuratov.feature.categoryproducts.domain.CategoryProductsRepository
import dev.ymuratov.feature.categoryproducts.presentation.model.CategoryProductsAction
import dev.ymuratov.feature.categoryproducts.presentation.model.CategoryProductsEvent
import dev.ymuratov.feature.categoryproducts.presentation.model.CategoryProductsState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class CategoryProductsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle, private val repository: CategoryProductsRepository
) : BaseViewModel<CategoryProductsState, CategoryProductsEvent, CategoryProductsAction>(CategoryProductsState()) {

    private val args = savedStateHandle.toRoute<AppDestination.CategoryProducts>()

    override fun obtainEvent(viewEvent: CategoryProductsEvent) {
        when (viewEvent) {
            CategoryProductsEvent.OnDataRefresh -> refreshData()
        }
    }

    init {
        updateViewState { copy(selectedCategory = args.categorySlug) }
    }

    private fun refreshData() {
        val category = currentState.selectedCategory ?: return
        repository.getProducts(category).onStart {
            updateViewState {
                copy(isLoading = true, errorMessage = null)
            }
        }.onEach { list ->
            updateViewState { copy(products = list) }
        }.catch { t ->
            updateViewState {
                copy(errorMessage = t.message ?: "Unknown error", isLoading = false)
            }
        }.launchIn(viewModelScope)
    }
}


