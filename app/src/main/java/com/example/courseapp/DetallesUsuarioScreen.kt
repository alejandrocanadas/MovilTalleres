package com.example.courseapp

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import io.ktor.http.Url
import androidx.compose.ui.platform.LocalContext
import com.example.courseapp.components.AnimatedBackground
import com.example.courseapp.components.AnimatedText
import com.example.courseapp.components.UserImageWithBorder




@Composable
fun DetallesUsuarioScreen(viewModel: DetalleUsuarioViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val contexto = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchUser()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        //bono primera parte del background
        AnimatedBackground()
        when {
            uiState.isLoading -> CircularProgressIndicator()
            uiState.error != null -> Text(text = "Error: ${uiState.error}", color = MaterialTheme.colorScheme.error)
            uiState.usuario != null -> {
                val usuario = uiState.usuario!!

                //BONO del taller puesto en brightspace

                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Spacer(modifier = Modifier.height(50.dp))

                    // Imagen del usuario con borde animado
                    UserImageWithBorder(usuario.image)

                    Spacer(modifier = Modifier.height(16.dp))

                    // Nombre con gradiente animado
                    AnimatedText("${usuario.firstName} ${usuario.lastName}")

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Trabaja en: ${usuario.company.name}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Edad: ${usuario.age} años", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Fecha de Nacimiento: ${usuario.birthDate}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Género: ${usuario.gender}", style = MaterialTheme.typography.bodyMedium)
                    Text(
                        text = "Teléfono: ${usuario.phone}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .clickable {
                                val intent = Intent(Intent.ACTION_DIAL).apply{ //requisito para las llamadas (link del brightspace)
                                    data = Uri.parse("tel:${usuario.phone}")
                                }
                                contexto.startActivity(intent)
                            }
                    ) //mas info
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








/*


@Composable
fun DetallesUsuarioScreen(viewModel: DetalleUsuarioViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val contexto = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // ⬇️ AGREGAMOS EL FONDO ANIMADO AQUÍ PARA QUE SE VEA CORRECTAMENTE


        when {
            uiState.isLoading -> CircularProgressIndicator()
            uiState.error != null -> Text(
                text = "Error: ${uiState.error}",
                color = MaterialTheme.colorScheme.error
            )

            uiState.usuario != null -> {
                val usuario = uiState.usuario!!

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize().padding(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(50.dp))

                    // Imagen del usuario con borde animado (Se mantiene)
                    UserImageWithBorder(usuario.image)

                    Spacer(modifier = Modifier.height(16.dp))

                    // Nombre con gradiente animado (Eliminamos la otra repetida)
                    AnimatedText("${usuario.firstName} ${usuario.lastName}")

                    Spacer(modifier = Modifier.height(8.dp))

                    // Información del usuario (Se mantiene)
                    Text(text = "Trabaja en: ${usuario.company.name}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Edad: ${usuario.age} años", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Fecha de Nacimiento: ${usuario.birthDate}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Género: ${usuario.gender}", style = MaterialTheme.typography.bodyMedium)

                    Text(
                        text = "Teléfono: ${usuario.phone}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.clickable {
                            val intent = Intent(Intent.ACTION_DIAL).apply {
                                data = Uri.parse("tel:${usuario.phone}")
                            }
                            contexto.startActivity(intent)
                        }
                    )

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

 */