package dev.ymuratov.feature.categoryproducts.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import dev.ymuratov.core.ui.components.AppToolbar
import dev.ymuratov.core.ui.components.ErrorView
import dev.ymuratov.core.ui.components.LoadingView
import dev.ymuratov.core.ui.utils.collectFlowWithLifecycle
import dev.ymuratov.feature.categoryproducts.presentation.model.CategoryProductsAction
import dev.ymuratov.feature.categoryproducts.presentation.model.CategoryProductsEvent
import dev.ymuratov.feature.categoryproducts.presentation.model.CategoryProductsState
import dev.ymuratov.feature.categoryproducts.presentation.viewmodel.CategoryProductsViewModel

@Composable
fun CategoryProductsContainer(
    modifier: Modifier = Modifier, viewModel: CategoryProductsViewModel = hiltViewModel(), navigateToProductDetails: (Int) -> Unit
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    viewModel.viewActions.collectFlowWithLifecycle { action ->
        when (action) {
            is CategoryProductsAction.NavigateToProductDetails -> navigateToProductDetails(action.productId)
            null -> {}
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> viewModel.obtainEvent(CategoryProductsEvent.OnDataRefresh)
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
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
        modifier = modifier, topBar = { state.selectedCategory?.let { AppToolbar(title = it) } }) { padding ->
        when {
            state.isLoading -> LoadingView(Modifier.fillMaxSize())
            state.errorMessage != null -> ErrorView(
                message = state.errorMessage,
                onRetry = { onEvent(CategoryProductsEvent.OnDataRefresh) },
                modifier = Modifier.fillMaxSize()
            )

            else -> {
                LazyColumn(contentPadding = padding) {
                    items(state.products) { product ->
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)),
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth()
                                .clickable { onEvent(CategoryProductsEvent.OnProductSelect(product.id)) }) {
                            AsyncImage(
                                model = product.thumbnail,
                                contentDescription = product.title,
                            )
                            Text(
                                text = product.title,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                color = Color(0xFFE65100)
                            )
                            Text(
                                text = $$"$$${product.price}  •  ⭐ $${product.rating}",
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 0.dp),
                                color = Color(0xFFBF360C)
                            )
                        }
                    }
                }
            }
        }
    }
}


