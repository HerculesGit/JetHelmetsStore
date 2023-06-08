package com.herco.jethelmetsstore.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.herco.jethelmetsstore.ui.theme.JetHelmetsStoreTheme


@Preview
@Composable
fun SearchFieldPreview() {
    HelmetSearchField(onValueChange = {})
}


@Composable
fun HelmetSearchField(
    onValueChange: (String) -> Unit,
) {
    JetHelmetsStoreTheme {
        val textState = remember { mutableStateOf("") }
        TextField(
            value = textState.value,
            onValueChange = {
                textState.value = it
                onValueChange(it)
            },
            label = {
                Text(
                    "Carl Pekerjaan...",
                    style = TextStyle(color = MaterialTheme.colorScheme.outlineVariant)
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = null
                )
            },

            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Color.White,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = MaterialTheme.colorScheme.outline
            ),
            textStyle = MaterialTheme.typography.bodySmall,
        )
    }
}