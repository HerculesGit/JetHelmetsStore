package com.herco.jethelmetsstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.herco.jethelmetsstore.presentation.screen.HelmetDetailScreen
import com.herco.jethelmetsstore.presentation.screen.HomeScreen
import com.herco.jethelmetsstore.ui.theme.JetHelmetsStoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelmetDetailScreen()
        }
    }
}
