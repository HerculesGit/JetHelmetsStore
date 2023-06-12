package com.herco.jethelmetsstore.presentation.screen.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.herco.jethelmetsstore.R
import com.herco.jethelmetsstore.di.UseCaseModule
import com.herco.jethelmetsstore.presentation.AppConstants
import com.herco.jethelmetsstore.presentation.model.Product
import com.herco.jethelmetsstore.presentation.rememberLifecycleEvent
import com.herco.jethelmetsstore.ui.theme.JetHelmetsStoreTheme

@Preview
@Composable
fun HelmetDetailScreenPreview() {
    HelmetDetailScreen(
        navController = rememberNavController(),
        productId = "0",
        viewModel = HelmetDetailViewModel(UseCaseModule.provideGetProductUseCase())

    )
}

@Composable
fun HelmetDetailScreen(
    navController: NavController,
    productId: String?,
    viewModel: HelmetDetailViewModel
) {
    val lifecycleEvent = rememberLifecycleEvent()

    LaunchedEffect(lifecycleEvent) {
        if (lifecycleEvent == Lifecycle.Event.ON_RESUME) {
            viewModel.fetchHelmet(productId = productId!!)
        }
    }

    val productUiState by viewModel.uiState.collectAsState()

    JetHelmetsStoreTheme {
        Scaffold(
            topBar = {
                AppBar(
                    onBackTapped = { navController.popBackStack() }, viewModel = viewModel
                )
            },
            bottomBar = {
                if (productUiState.loading) Box {} else Button(
                    onClick = { },
                    shape = RoundedCornerShape(size = 15.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = AppConstants.mediumMargin),
                ) {
                    Text(
                        text = "Add to Bag", modifier = Modifier
                            .padding(vertical = AppConstants.smallMargin)
                    )
                }
            },
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            if (productUiState.loading) Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    Text(text = "Loading...")
                }
            }
            else
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(it)
                        .padding(top = AppConstants.largeMargin)
                        .fillMaxSize()
                ) {
                    Body(
                        productUiState = viewModel.uiState.collectAsState().value,
                        viewModel = viewModel
                    )
                }
        }
    }
}

@Preview
@Composable
fun BodyPreview() {
    val uiState = ProductUiState(
        loading = false,
        product = Product("1"),
        productSize = "XS"
    )
    val viewModel: HelmetDetailViewModel = viewModel()

    JetHelmetsStoreTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.surface)) {
            Body(productUiState = uiState, viewModel = viewModel)
        }
    }
}

@Composable
fun Body(productUiState: ProductUiState, viewModel: HelmetDetailViewModel) {
    val product = productUiState.product!!
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = AppConstants.mediumMargin)
            .fillMaxSize(),
    ) {
        Text(
            text = product.brand.uppercase(), style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = product.name, style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = "R$${product.price}",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(AppConstants.mediumMargin))
        HelmetImageDetails()
        ChooseHelmetSize(
            sizes = product.sizes,
            viewModel = viewModel
        )
        Text(
            text = product.details,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun HelmetImageDetails() {
    val piecesOfTheHelmet = listOf(
        Icons.Rounded.Favorite,
        Icons.Rounded.Favorite,
        Icons.Rounded.Favorite,
        Icons.Rounded.Favorite,
    )
    val selectedPieceIndex = 0

    Row(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .padding(end = AppConstants.mediumMargin)
                .fillMaxHeight(),
//                .padding(bottom = AppConstants.largeMargin),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            piecesOfTheHelmet.mapIndexed { index, icon ->
                val border = if (selectedPieceIndex == index) BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary
                ) else null

                Surface(
                    shape = CircleShape,
                    border = border,
                ) {
                    Icon(
                        imageVector = icon,
                        modifier = Modifier.padding(all = AppConstants.smallMargin),
                        contentDescription = ""
                    )
                }

            }.toList()
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_helmet_jet),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun ChooseHelmetSize(sizes: List<String>, viewModel: HelmetDetailViewModel) {

    val productUiState by viewModel.uiState.collectAsState()

    LazyRow(
        modifier = Modifier.padding(vertical = AppConstants.mediumMargin),
        content = {
            items(sizes) {

                val color =
                    if (it == productUiState.productSize) MaterialTheme.colorScheme.primary else
                        MaterialTheme.colorScheme.onSurfaceVariant

                Box(modifier = Modifier.padding(end = AppConstants.mediumMargin)) {
                    Surface(
                        border = BorderStroke(1.dp, color),
                        shape = RoundedCornerShape(size = 15.dp),
                        modifier = Modifier.size(45.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable { viewModel.updateProductSize(it) },
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) { Text(text = it, color = color) }
                    }
                }
            }
        })
}

@Composable
private fun AppBar(onBackTapped: () -> Unit, viewModel: HelmetDetailViewModel) {
    val iconPadding = AppConstants.smallMargin * 1.5f
    val uiState by viewModel.uiState.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = AppConstants.mediumMargin)
            .padding(top = AppConstants.mediumMargin),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            border = BorderStroke(0.5.dp, Color.Gray.copy(alpha = 0.5f)),
            shape = RoundedCornerShape(size = 15.dp)
        ) {
            Box(
                modifier =
                Modifier.clickable { onBackTapped() },
            ) {
                Icon(
                    Icons.Rounded.ArrowBack,
                    modifier = Modifier.padding(all = iconPadding),
                    contentDescription = stringResource(id = R.string.menu_hamburger)
                )
            }
        }
        Surface(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface),
            border = BorderStroke(0.5.dp, Color.Gray.copy(alpha = 0.5f)),
            shape = RoundedCornerShape(size = 15.dp)
        ) {
            if (!uiState.loading)
                Box(
                    modifier =
                    Modifier.clickable {
                        viewModel.toggleFavorite()
                    },
                ) {
                    Icon(
                        if (uiState.isFavorite) Icons.Rounded.Favorite else Icons.Outlined.FavoriteBorder,
                        modifier = Modifier
                            .padding(iconPadding),
                        contentDescription = stringResource(id = R.string.menu_hamburger),
                        tint = if (uiState.isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                    )
                }
        }
    }

}