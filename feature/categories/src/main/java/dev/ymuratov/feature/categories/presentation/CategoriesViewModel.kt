package dev.ymuratov.feature.categories.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ymuratov.core.models.CategoryModel
import dev.ymuratov.feature.categories.domain.CategoriesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CategoriesUiState(
    val isLoading: Boolean = false, val data: List<CategoryModel> = emptyList(), val error: String? = null
)

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoriesRepository: CategoriesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(CategoriesUiState(isLoading = true))
    val uiState: StateFlow<CategoriesUiState> = _uiState.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        _uiState.value = CategoriesUiState(isLoading = true)
        viewModelScope.launch {
            try {
                categoriesRepository.getCategories().collect { list ->
                    _uiState.value = CategoriesUiState(data = list)
                }
            } catch (t: Throwable) {
                _uiState.value = CategoriesUiState(error = t.message ?: "Unknown error")
            }
        }
    }
}


