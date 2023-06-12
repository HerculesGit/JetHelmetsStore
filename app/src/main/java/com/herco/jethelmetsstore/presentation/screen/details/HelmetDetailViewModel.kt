package com.herco.jethelmetsstore.presentation.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herco.jethelmetsstore.domain.result.Resource
import com.herco.jethelmetsstore.domain.usecase.GetProductUseCase
import com.herco.jethelmetsstore.presentation.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class ProductUiState(
    val loading: Boolean = true,
    val product: Product? = null,
    val productSize: String? = "XS",
    val isFavorite: Boolean = false,
    val msgError: String? = null,
)

class HelmetDetailViewModel constructor(private val getProductUseCase: GetProductUseCase) :
    ViewModel() {

    // Expose screen UI state
    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    fun fetchHelmet(productId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, product = null, productSize = null) }

            when (val result = getProductUseCase.execute(productId)) {
                is Resource.Success -> {
                    val product = result.data;

                    _uiState.update {
                        it.copy(
                            loading = false,
                            product = product,
                            productSize = if (product.sizes.isNotEmpty()) product.sizes.first() else "",
                            isFavorite = product.favorite,
                            msgError = null
                        )
                    }
                }

                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            loading = false,
                            product = null,
                            productSize = null,
                            isFavorite = false,
                            msgError = result.message
                        )
                    }
                }
            }
        }
    }

    fun updateProductSize(productSize: String) {
        _uiState.update { it.copy(productSize = productSize) }
    }

    fun toggleFavorite() {
        _uiState.update { it.copy(isFavorite = !it.isFavorite) }
    }

}