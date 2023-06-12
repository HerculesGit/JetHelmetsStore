package com.herco.jethelmetsstore.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herco.jethelmetsstore.domain.result.Resource
import com.herco.jethelmetsstore.domain.usecase.GetPopularHelmetsUseCase
import com.herco.jethelmetsstore.presentation.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class HomeUiState(
    val loading: Boolean = true,
    val products: List<Product> = emptyList(),
    val msgError: String? = null,
)

class HomeViewModel(private val getPopularHelmetsUseCase: GetPopularHelmetsUseCase) : ViewModel() {

    // Expose screen UI state
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun fetchPopularProducts() {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, products = emptyList(), msgError = null) }

            when (val result = getPopularHelmetsUseCase.execute()) {
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(loading = false, products = result.data)
                    }
                }

                is Resource.Error -> {
                    _uiState.update {
                        it.copy(msgError = result.message, loading = false)
                    }
                }
            }

        }
    }

}