package dev.ymuratov.feature.productdetail.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.ymuratov.core.ui.R
import dev.ymuratov.core.ui.components.AppIconButton
import dev.ymuratov.core.ui.components.AppToolbar
import dev.ymuratov.core.ui.components.ErrorView
import dev.ymuratov.core.ui.components.LoadingView
import dev.ymuratov.core.ui.theme.AppTheme
import dev.ymuratov.core.ui.utils.OnLifecycleEvent
import dev.ymuratov.core.ui.utils.collectFlowWithLifecycle
import dev.ymuratov.feature.productdetail.presentation.component.AppPagerIndicator
import dev.ymuratov.feature.productdetail.presentation.component.PriceBlock
import dev.ymuratov.feature.productdetail.presentation.component.ProductImagesPager
import dev.ymuratov.feature.productdetail.presentation.component.ProductReviews
import dev.ymuratov.feature.productdetail.presentation.component.ProductTags
import dev.ymuratov.feature.productdetail.presentation.model.ProductDetailAction
import dev.ymuratov.feature.productdetail.presentation.model.ProductDetailEvent
import dev.ymuratov.feature.productdetail.presentation.model.ProductDetailState
import dev.ymuratov.feature.productdetail.presentation.viewmodel.ProductDetailViewModel

@Composable
fun ProductDetailContainer(
    modifier: Modifier = Modifier,
    viewModel: ProductDetailViewModel = hiltViewModel(),
    navigateUp: () -> Unit = {},
    navigateToHome: () -> Unit = {}
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    viewModel.viewActions.collectFlowWithLifecycle { action ->
        when (action) {
            ProductDetailAction.NavigateUp -> navigateUp()
            ProductDetailAction.NavigateToHome -> navigateToHome()
            null -> {}
        }
    }
    OnLifecycleEvent(Lifecycle.Event.ON_RESUME) {
        viewModel.obtainEvent(ProductDetailEvent.OnDataRefresh)
    }
    ProductDetailContent(modifier = modifier, state = state, onEvent = viewModel::obtainEvent)
}

@Composable
fun ProductDetailContent(
    modifier: Modifier = Modifier, state: ProductDetailState = ProductDetailState(), onEvent: (ProductDetailEvent) -> Unit = {}
) {
    val imagesCount = state.productInfo?.images?.size ?: 0
    val imagesPagerState = rememberPagerState(pageCount = { imagesCount })
    val reviewsCount = state.productInfo?.reviews?.size ?: 0
    val reviewsPagerState = rememberPagerState(pageCount = { reviewsCount })

    Scaffold(modifier = modifier, topBar = {
        AppToolbar(title = state.productInfo?.title ?: "Product", navigationIcon = {
            AppIconButton(icon = R.drawable.ic_arrow_back) {
                onEvent(ProductDetailEvent.OnNavigateUp)
            }
        })
    }, bottomBar = {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = AppTheme.colors.backgroundSecondary)
                .padding(vertical = 16.dp)
                .navigationBarsPadding(), contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { onEvent(ProductDetailEvent.OnNavigateUp) },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(dev.ymuratov.feature.productdetail.R.string.buy_now_button),
                    color = AppTheme.colors.buttonTextPrimary,
                    style = AppTheme.typography.buttonMedium
                )
            }
        }
    }) { innerPadding ->
        Box(
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding(), bottom = innerPadding.calculateBottomPadding()),
            contentAlignment = Alignment.Center
        ) {
            when {
                state.isLoading -> LoadingView(Modifier.fillMaxSize())
                state.errorMessage != null -> ErrorView(
                    message = state.errorMessage,
                    onRetry = { onEvent(ProductDetailEvent.OnDataRefresh) },
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .fillMaxSize()
                )

                else -> if (state.productInfo == null) {
                    ErrorView(
                        message = stringResource(dev.ymuratov.feature.productdetail.R.string.product_not_found_error_text),
                        onRetry = { onEvent(ProductDetailEvent.OnDataRefresh) },
                        modifier = Modifier
                            .padding(horizontal = 32.dp)
                            .fillMaxSize()
                    )
                } else {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        color = AppTheme.colors.backgroundSecondary,
                                        shape = RoundedCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp)
                                    )
                            ) {
                                Box {
                                    ProductImagesPager(imagesPagerState, state.productInfo.images)
                                    if (imagesCount > 1) {
                                        AppPagerIndicator(
                                            pageCount = imagesCount,
                                            selectedPage = imagesPagerState.currentPage,
                                            modifier = Modifier
                                                .padding(16.dp)
                                                .align(Alignment.BottomStart)
                                        )
                                    }
                                }
                                Spacer(Modifier.size(16.dp))
                                PriceBlock(
                                    product = state.productInfo, modifier = Modifier
                                        .padding(horizontal = 16.dp)
                                        .fillMaxWidth()
                                )
                                Spacer(Modifier.size(16.dp))
                            }
                            Spacer(Modifier.size(16.dp))
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        color = AppTheme.colors.backgroundSecondary, shape = RoundedCornerShape(24.dp)
                                    )
                                    .padding(vertical = 16.dp)
                            ) {
                                ProductTags(state.productInfo.tags)
                                Spacer(Modifier.size(12.dp))
                                Text(
                                    text = if (state.productInfo.brand != null) "${state.productInfo.title} - ${state.productInfo.brand}"
                                    else state.productInfo.title,
                                    color = AppTheme.colors.textPrimary,
                                    style = AppTheme.typography.titleLarge,
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp)
                                        .fillMaxWidth()
                                )
                                Spacer(Modifier.size(4.dp))
                                Text(
                                    text = state.productInfo.description,
                                    color = AppTheme.colors.textPrimary,
                                    style = AppTheme.typography.bodyMedium,
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp)
                                        .fillMaxWidth()
                                )
                            }
                            Spacer(Modifier.size(16.dp))
                            if (state.productInfo.reviews.isNotEmpty()) {
                                ProductReviews(state.productInfo, reviewsPagerState)
                            }
                            Spacer(Modifier.size(16.dp))
                        }
                    }
                }
            }
        }
    }
}


