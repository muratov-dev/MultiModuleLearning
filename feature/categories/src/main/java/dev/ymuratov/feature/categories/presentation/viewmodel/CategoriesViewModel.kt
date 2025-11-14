package dev.ymuratov.feature.categories.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ymuratov.core.ui.viewmodel.BaseViewModel
import dev.ymuratov.feature.categories.domain.CategoriesRepository
import dev.ymuratov.feature.categories.presentation.model.CategoriesAction
import dev.ymuratov.feature.categories.presentation.model.CategoriesEvent
import dev.ymuratov.feature.categories.presentation.model.CategoriesState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoriesRepository: CategoriesRepository
) : BaseViewModel<CategoriesState, CategoriesEvent, CategoriesAction>(CategoriesState()) {

    override fun obtainEvent(viewEvent: CategoriesEvent) {
        when (viewEvent) {
            is CategoriesEvent.OnCategorySelect -> sendAction(CategoriesAction.NavigateToCategoryProducts(viewEvent.categorySlug))
            CategoriesEvent.OnDataRefresh -> refreshData()
        }
    }

    private fun refreshData() {
        categoriesRepository.getCategories().onStart {
            updateViewState {
                copy(isLoading = true, errorMessage = null)
            }
        }.onEach { list ->
            updateViewState { copy(categories = list) }
        }.catch { t ->
            updateViewState {
                copy(errorMessage = t.message ?: "Unknown error", isLoading = false)
            }
        }.launchIn(viewModelScope)
    }
}


