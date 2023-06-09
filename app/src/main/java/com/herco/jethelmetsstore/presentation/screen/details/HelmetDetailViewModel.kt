package com.herco.jethelmetsstore.presentation.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herco.jethelmetsstore.presentation.model.Product
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class ProductUiState(
    val loading: Boolean = true,
    val product: Product? = null,
    val productSize: String? = "XS"
)

class HelmetDetailViewModel : ViewModel() {

    // Expose screen UI state
    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    fun fetchHelmet(productId: String) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(loading = true, product = null, productSize = null)
            }
            delay(timeMillis = 1000)
            _uiState.update {
                it.copy(
                    loading = false,
                    product = Product(id = productId),
                    productSize = "XS"
                )
            }

        }
    }

    fun updateProductSize(productSize: String) {
        _uiState.update { it.copy(productSize = productSize) }
    }

}