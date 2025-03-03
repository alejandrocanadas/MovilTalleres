package com.example.courseapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class DetalleUsuarioUiState(
    val usuario: Usuario? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class DetalleUsuarioViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DetalleUsuarioUiState())
    val uiState: StateFlow<DetalleUsuarioUiState> = _uiState

    fun fetchUser() {
        viewModelScope.launch {
            val usuario = DetalleUsuarioSingleton.getUsuarioSeleccionado()
            if (usuario != null) {
                _uiState.value = DetalleUsuarioUiState(usuario = usuario)
            } else {
                _uiState.value = DetalleUsuarioUiState(error = "No se encontró el usuario")
            }
        }
    }
}
