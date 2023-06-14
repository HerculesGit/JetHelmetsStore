package com.herco.jethelmetsstore.presentation.screen.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.herco.jethelmetsstore.R
import com.herco.jethelmetsstore.di.UseCaseModule
import com.herco.jethelmetsstore.presentation.AppConstants
import com.herco.jethelmetsstore.presentation.component.HelmetSearchField
import com.herco.jethelmetsstore.presentation.model.Product
import com.herco.jethelmetsstore.presentation.navigation.HelmetDetailRoute
import com.herco.jethelmetsstore.presentation.navigation.params
import com.herco.jethelmetsstore.presentation.rememberLifecycleEvent
import com.herco.jethelmetsstore.ui.theme.JetHelmetsStoreTheme


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController(),
        viewModel = HomeViewModel(UseCaseModule(LocalContext.current).provideGetPopularHelmetsUseCase())
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel) {
    JetHelmetsStoreTheme {

        val uiState by viewModel.uiState.collectAsState()
        val lifecycleEvent = rememberLifecycleEvent()

        LaunchedEffect(lifecycleEvent) {
            if (lifecycleEvent == Lifecycle.Event.ON_RESUME) {
                viewModel.fetchPopularProducts()
            }
        }
        Scaffold(
            topBar = { TopAppBar(title = { AppBar() }) }
        ) { contentPadding ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(contentPadding)
            ) {

                if (uiState.loading) Column(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator()
                    Spacer(Modifier.height(AppConstants.smallMargin))
                    Text(text = "Loading...")
                }
                else if (uiState.msgError != null) Column(modifier = Modifier.fillMaxSize()) {
                    Text(text = "Ops!")
                    Spacer(Modifier.height(AppConstants.smallMargin))
                    Text(text = uiState.msgError!!)
                }
                else {
                    BuildBody(navController = navController, viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun BuildBody(navController: NavController, viewModel: HomeViewModel) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())

            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = AppConstants.largeMargin)
                .padding(horizontal = AppConstants.mediumMargin)
        ) {
            HelmetSearchField(onValueChange = {})
        }

        HelmetCategory()
        Spacer(modifier = Modifier.padding(vertical = AppConstants.mediumMargin))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AppConstants.mediumMargin),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Popular Products",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "See All", style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(modifier = Modifier.padding(vertical = AppConstants.smallMargin))
        PopularProducts(navController, viewModel)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PopularProducts(navController: NavController, viewModel: HomeViewModel) {
    val productSize: Dp = (LocalConfiguration.current.screenWidthDp.dp) / 2

    val uiState by viewModel.uiState.collectAsState()

    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        uiState.products.forEachIndexed { _, product ->
            Box(
                modifier = Modifier
                    .height(productSize * 1.5f - AppConstants.mediumMargin)
                    .width(productSize - AppConstants.smallMargin)
            ) {
                ProductItem(product, onTap = {
                    navController.navigate(
                        params(
                            route = HelmetDetailRoute.route,
                            argument = product.id
                        )
                    )
                })
            }
        }
    }
}

@Composable
fun ProductItem(product: Product, onTap: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .padding(horizontal = AppConstants.smallMargin)
            .padding(bottom = AppConstants.mediumMargin)
            .fillMaxWidth()
            .clickable { onTap() }
    ) {
        Surface(
            border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.5f)),
            shape = RoundedCornerShape(25),
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(bottom = AppConstants.smallMargin)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_helmet_logo),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }
        Column(verticalArrangement = Arrangement.Bottom) {
            Text(
                text = product.brand.uppercase(), style = MaterialTheme.typography.labelLarge.copy(
                    fontSize = MaterialTheme.typography.labelLarge.fontSize.div(1.1f)
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = product.name, style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = MaterialTheme.typography.titleLarge.fontSize.div(1.1f)
                ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = product.price.toString(), color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.labelLarge.fontSize.div(1.1f)
                    )
                )
                Surface(
                    border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.5f)),
                    shape = CircleShape,
                    modifier = Modifier.padding(all = AppConstants.smallMargin)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add, contentDescription = "Add to cart button",
                    )
                }
            }
        }
    }
}

@Composable
private fun AppBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Rounded.Menu,
            contentDescription = stringResource(id = R.string.menu_hamburger)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_helmet_logo),
            contentDescription = stringResource(id = R.string.app_name),
            modifier = Modifier.size(64.dp)
        )
        Box(
            modifier = Modifier.padding(end = AppConstants.mediumMargin)
        ) {
            Icon(imageVector = Icons.Rounded.Notifications, contentDescription = null)
        }
    }
}

@Composable
fun HelmetCategory() {
    val sizeCategory = 100.dp

    LazyRow(content = {
        items(listOf("Full Face", "Flip Up", "Jet")) {
            Box(modifier = Modifier.padding(start = AppConstants.mediumMargin)) {
                Surface(
                    modifier = Modifier
                        .height(sizeCategory * 1.2f)
                        .width(sizeCategory),
                    border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.5f)),
                    shape = RoundedCornerShape(30)
                ) {
                    Column(

                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(bottom = AppConstants.smallMargin)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_helmet_full_face),
                            contentDescription = "full face",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.height((100 * 0.7f).dp)
                        )

                        Text(text = it)
                    }

                }
            }

        }
    })
}