package dev.ymuratov.feature.productdetail.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import dev.ymuratov.core.ui.R
import dev.ymuratov.core.ui.components.AppToolbar
import dev.ymuratov.core.ui.components.ErrorView
import dev.ymuratov.core.ui.components.LoadingView
import dev.ymuratov.core.ui.utils.collectFlowWithLifecycle
import dev.ymuratov.feature.productdetail.presentation.model.ProductDetailAction
import dev.ymuratov.feature.productdetail.presentation.model.ProductDetailEvent
import dev.ymuratov.feature.productdetail.presentation.model.ProductDetailState
import dev.ymuratov.feature.productdetail.presentation.viewmodel.ProductDetailViewModel

@Composable
fun ProductDetailContainer(
    modifier: Modifier = Modifier, viewModel: ProductDetailViewModel = hiltViewModel(), navigateUp: () -> Unit = {}
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    viewModel.viewActions.collectFlowWithLifecycle { action ->
        when (action) {
            ProductDetailAction.NavigateUp -> navigateUp()
            null -> {}
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> viewModel.obtainEvent(ProductDetailEvent.OnDataRefresh)
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    ProductDetailContent(modifier = modifier, state = state, onEvent = viewModel::obtainEvent)
}

@Composable
fun ProductDetailContent(
    modifier: Modifier = Modifier, state: ProductDetailState = ProductDetailState(), onEvent: (ProductDetailEvent) -> Unit = {}
) {
    Scaffold(modifier = modifier, topBar = {
        AppToolbar(
            title = state.productInfo?.title ?: "Product", navigationIcon = {
                Box(
                    modifier = Modifier.clickable { onEvent(ProductDetailEvent.OnNavigateUp) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_back), contentDescription = null)
                }
            })
    }) { padding ->
        when {
            state.isLoading -> LoadingView(Modifier.fillMaxSize())
            state.errorMessage != null -> ErrorView(
                message = state.errorMessage,
                onRetry = { onEvent(ProductDetailEvent.OnDataRefresh) },
                modifier = Modifier.fillMaxSize()
            )

            else -> Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            ) {
                Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)), modifier = Modifier.fillMaxWidth()) {
                    AsyncImage(
                        model = state.productInfo?.images?.firstOrNull() ?: state.productInfo?.thumbnail,
                        contentDescription = state.productInfo?.title ?: ""
                    )
                    Text(text = state.productInfo?.title ?: "", modifier = Modifier.padding(12.dp), color = Color(0xFF1B5E20))
                    Text(text = "Brand: ${state.productInfo?.brand ?: ""}", modifier = Modifier.padding(horizontal = 12.dp))
                    Text(
                        text = "Price: $${state.productInfo?.price ?: 0.0} • Stock: ${state.productInfo?.stock ?: 0}",
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                    Text(
                        text = "Discount: ${state.productInfo?.discountPercentage ?: 0.0}% • Rating: ${state.productInfo?.rating ?: 0.0}",
                        modifier = Modifier.padding(12.dp)
                    )
                }
                Text(text = state.productInfo?.description ?: "", modifier = Modifier.padding(12.dp))
                Button(
                    onClick = { onEvent(ProductDetailEvent.OnNavigateUp) }, modifier = Modifier.padding(12.dp)
                ) { Text("Select") }
            }
        }
    }
}


