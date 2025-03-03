package com.example.courseapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun DetallesUsuarioScreen(viewModel: DetalleUsuarioViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchUser()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            uiState.isLoading -> CircularProgressIndicator()
            uiState.error != null -> Text(text = "Error: ${uiState.error}", color = MaterialTheme.colorScheme.error)
            uiState.usuario != null -> {
                val usuario = uiState.usuario!!
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = rememberAsyncImagePainter(usuario.image),
                        contentDescription = "Foto de ${usuario.firstName} ${usuario.lastName}",
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "${usuario.firstName} ${usuario.lastName}", style = MaterialTheme.typography.headlineMedium)
                    Text(text = "Trabaja en: ${usuario.company.name}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Edad: ${usuario.age} años", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Fecha de Nacimiento: ${usuario.birthDate}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Género: ${usuario.gender}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Teléfono: ${usuario.phone}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Email: ${usuario.email}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Usuario: ${usuario.username}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Contraseña: ${usuario.password}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Grupo Sanguíneo: ${usuario.bloodGroup}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Altura: ${usuario.height} cm", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Peso: ${usuario.weight} kg", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Color de Ojos: ${usuario.eyeColor}", style = MaterialTheme.typography.bodyMedium)

                }
            }
        }
    }
}
