package dev.ymuratov.feature.categoryproducts.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.ymuratov.core.ui.R
import dev.ymuratov.core.ui.components.AppIconButton
import dev.ymuratov.core.ui.components.AppToolbar
import dev.ymuratov.core.ui.components.ErrorView
import dev.ymuratov.core.ui.components.LoadingView
import dev.ymuratov.core.ui.utils.OnLifecycleEvent
import dev.ymuratov.core.ui.utils.collectFlowWithLifecycle
import dev.ymuratov.feature.categoryproducts.presentation.component.ProductCard
import dev.ymuratov.feature.categoryproducts.presentation.model.CategoryProductsAction
import dev.ymuratov.feature.categoryproducts.presentation.model.CategoryProductsEvent
import dev.ymuratov.feature.categoryproducts.presentation.model.CategoryProductsState
import dev.ymuratov.feature.categoryproducts.presentation.viewmodel.CategoryProductsViewModel

@Composable
fun CategoryProductsContainer(
    modifier: Modifier = Modifier,
    viewModel: CategoryProductsViewModel = hiltViewModel(),
    navigateToProductDetails: (Int) -> Unit,
    navigateUp: () -> Unit = {}
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    viewModel.viewActions.collectFlowWithLifecycle { action ->
        when (action) {
            is CategoryProductsAction.NavigateToProductDetails -> navigateToProductDetails(action.productId)
            CategoryProductsAction.NavigateUp -> navigateUp()
            null -> {}
        }
    }

    OnLifecycleEvent(Lifecycle.Event.ON_RESUME) {
        viewModel.obtainEvent(CategoryProductsEvent.OnDataRefresh)
    }
    CategoryProductsScreen(modifier = modifier, state = state, onEvent = viewModel::obtainEvent)
}

@Composable
fun CategoryProductsScreen(
    modifier: Modifier = Modifier,
    state: CategoryProductsState = CategoryProductsState(),
    onEvent: (CategoryProductsEvent) -> Unit = {}
) {
    Scaffold(
        modifier = modifier, topBar = {
            state.selectedCategoryTitle?.let { category ->
                AppToolbar(title = category, navigationIcon = {
                    AppIconButton(icon = R.drawable.ic_arrow_back) {
                        onEvent(CategoryProductsEvent.OnNavigateUp)
                    }
                })
            }
        }) { innerPadding ->
        Box(modifier = Modifier.padding(top = innerPadding.calculateTopPadding()), contentAlignment = Alignment.Center) {
            when {
                state.isLoading -> LoadingView(Modifier.fillMaxSize())
                state.errorMessage != null -> ErrorView(
                    message = state.errorMessage,
                    onRetry = { onEvent(CategoryProductsEvent.OnDataRefresh) },
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .fillMaxSize()
                )

                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(top = 16.dp, bottom = innerPadding.calculateBottomPadding())
                    ) {
                        items(state.products) { product ->
                            ProductCard(product = product) {
                                onEvent(CategoryProductsEvent.OnProductSelect(it.id))
                            }
                        }
                    }
                }
            }
        }
    }
}


