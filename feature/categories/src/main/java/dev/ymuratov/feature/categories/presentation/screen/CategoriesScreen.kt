package dev.ymuratov.feature.categories.presentation.screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.ymuratov.core.ui.components.AppToolbar
import dev.ymuratov.core.ui.components.ErrorView
import dev.ymuratov.core.ui.components.LoadingView
import dev.ymuratov.core.ui.utils.collectFlowWithLifecycle
import dev.ymuratov.feature.categories.presentation.component.CategoryCard
import dev.ymuratov.feature.categories.presentation.model.CategoriesAction
import dev.ymuratov.feature.categories.presentation.model.CategoriesEvent
import dev.ymuratov.feature.categories.presentation.model.CategoriesState
import dev.ymuratov.feature.categories.presentation.viewmodel.CategoriesViewModel

@Composable
fun CategoriesContainer(
    modifier: Modifier = Modifier,
    viewModel: CategoriesViewModel = hiltViewModel(),
    navigateToCategoryProducts: (String) -> Unit = {}
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    viewModel.viewActions.collectFlowWithLifecycle { action ->
        when (action) {
            is CategoriesAction.NavigateToCategoryProducts -> navigateToCategoryProducts(action.categorySlug)
            null -> {}
        }
    }
    CategoriesContent(modifier = modifier, state = state, onEvent = viewModel::obtainEvent)
}

@Composable
private fun CategoriesContent(
    modifier: Modifier = Modifier, state: CategoriesState = CategoriesState(), onEvent: (CategoriesEvent) -> Unit = {}
) {
    Scaffold(
        modifier = modifier, topBar = { AppToolbar(title = "Categories") }) { innerPadding ->
        Box(modifier = Modifier.padding(top = innerPadding.calculateTopPadding()), contentAlignment = Alignment.Center) {
            when {
                state.isLoading -> LoadingView(modifier = Modifier.fillMaxSize())
                state.errorMessage != null -> ErrorView(
                    message = state.errorMessage,
                    onRetry = { onEvent(CategoriesEvent.OnDataRefresh) },
                    modifier = Modifier.fillMaxSize()
                )

                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(state.categories) { category ->
                            CategoryCard(category = category, onCategoryClick = { onEvent(CategoriesEvent.OnCategorySelect(it)) })
                        }
                    }
                }
            }
        }
    }
}

