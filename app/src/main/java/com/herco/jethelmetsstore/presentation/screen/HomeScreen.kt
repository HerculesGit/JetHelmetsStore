package com.herco.jethelmetsstore.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.herco.jethelmetsstore.R
import com.herco.jethelmetsstore.presentation.AppConstants
import com.herco.jethelmetsstore.presentation.component.HelmetSearchField
import com.herco.jethelmetsstore.ui.theme.JetHelmetsStoreTheme


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreen() {
    JetHelmetsStoreTheme {
        Scaffold(
            topBar = { TopAppBar(title = { AppBar() }) }
        ) { contentPadding ->
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(contentPadding)
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
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Popular Products")
                        Text(text = "See All")
                    }
                    Spacer(modifier = Modifier.padding(vertical = AppConstants.smallMargin))
                    PopularProducts()
                }
            }
        }
    }
}

data class Product(
    val brand: String = "jet helmet",
    val name: String = "Caberg Riveira",
    val price: Double = 65.25,
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PopularProducts() {
    val popularProducts = List(size = 10) { Product() }
    val productSize: Dp = (LocalConfiguration.current.screenWidthDp.dp) / 2

    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        popularProducts.forEachIndexed { index, product ->
            Box(
                modifier = Modifier
                    .height(productSize * 1.5f - AppConstants.mediumMargin)
                    .width(productSize - AppConstants.smallMargin)
            ) {
                ProductItem(product)
            }
        }
    }
//    Column(
////        columns = GridCells.Fixed(2),
//
////        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
//        content = {
////            items(popularProducts) { ProductItem(it) }
//            ProductItem(popularProducts.first())
//        },
////        userScrollEnabled = false
//    )
}

@Composable
fun ProductItem(product: Product) {
    Column(
        modifier = Modifier
            .padding(horizontal = AppConstants.smallMargin)
            .padding(bottom = AppConstants.mediumMargin)
            .fillMaxWidth()
    ) {
        Surface(
            border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.5f)),
            shape = RoundedCornerShape(25),
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_helmet_logo),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }
        Column(
            Modifier.weight(1f),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(text = product.name)
            Text(text = product.brand)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = product.price.toString(), color = MaterialTheme.colorScheme.primary)
                Icon(
                    imageVector = Icons.Rounded.Add, contentDescription = "Add to cart button",
                )
            }
        }
    }
}

@Composable
fun AppBar() {
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
        Icon(imageVector = Icons.Rounded.Notifications, contentDescription = null)
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