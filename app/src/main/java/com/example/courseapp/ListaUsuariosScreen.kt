package com.example.courseapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun ListaUsuariosScreen(navController: NavController) {
    val usuariosState = produceState<List<Usuario>?>(initialValue = null) {
        value = fetchUsers()
    }

    if (usuariosState.value == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    val usuarios = usuariosState.value ?: emptyList()

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        items(usuarios) { usuario ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        DetalleUsuarioSingleton.setUsuarioSeleccionado(usuario)
                        navController.navigate("detallesUsuario")
                    }
            ) {
                Row(modifier = Modifier.padding(16.dp)) {
                    Image(
                        painter = rememberAsyncImagePainter(usuario.image),
                        contentDescription = "Imagen de ${usuario.firstName}",
                        modifier = Modifier.size(50.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(text = "${usuario.firstName} ${usuario.lastName}", style = MaterialTheme.typography.titleLarge)
                        Text(text = usuario.company.name, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

suspend fun fetchUsers(): List<Usuario> {
    return withContext(Dispatchers.IO) {
        try {
            val api = Api()
            api.getUsers()
        } catch (e: Exception) {
            emptyList()
        }
    }
}

