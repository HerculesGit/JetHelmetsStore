package com.herco.jethelmetsstore.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.herco.jethelmetsstore.R
import com.herco.jethelmetsstore.presentation.AppConstants
import com.herco.jethelmetsstore.ui.theme.JetHelmetsStoreTheme

@Preview
@Composable
fun HelmetDetailScreen() {
    val product = Product()

    JetHelmetsStoreTheme {
        Scaffold(
            topBar = { AppBar() },
            bottomBar = {
                Button(
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
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(it)
                    .padding(top = AppConstants.largeMargin)
                    .fillMaxSize()
            ) {
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = AppConstants.mediumMargin)
                        .fillMaxSize()
                ) {
                    Text(text = product.brand)
                    Text(text = product.name)
                    Text(
                        text = product.price.toString(),
                        color = MaterialTheme.colorScheme.primary
                    )

                    HelmetImageDetails()
                    ChooseHelmetSize(sizes = listOf("XS", "S", "M", "L", "XL"))
                    Text(text = product.details)

                    //                    Button(
                    //                        onClick = { },
                    //                        shape = RoundedCornerShape(size = 15.dp),
                    //                        modifier = Modifier.fillMaxWidth()
                    //                    ) {
                    //                        Text(text = "Add to Bag")
                    //                    }
                }
            }
        }
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
fun ChooseHelmetSize(sizes: List<String>) {
    val selectedSize = "XS"

    LazyRow(
        modifier = Modifier.padding(vertical = AppConstants.mediumMargin),
        content = {
            items(sizes) {

                val color = if (it == selectedSize) MaterialTheme.colorScheme.primary else
                    MaterialTheme.colorScheme.onSurfaceVariant

                Box(modifier = Modifier.padding(end = AppConstants.mediumMargin)) {
                    Surface(
                        border = BorderStroke(1.dp, color),
                        shape = RoundedCornerShape(size = 15.dp),
                        modifier = Modifier.size(45.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) { Text(text = it, color = color) }
                    }
                }
            }
        })
}

@Composable
private fun AppBar() {
    val iconPadding = AppConstants.smallMargin * 1.5f
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
            Box {
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
            Icon(
                Icons.Rounded.Favorite,
                modifier = Modifier.padding(iconPadding),
                contentDescription = stringResource(id = R.string.menu_hamburger),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }

}