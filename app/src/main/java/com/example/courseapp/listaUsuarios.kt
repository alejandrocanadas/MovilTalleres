package com.example.courseapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.courseapp.ui.theme.PurpleCustom
import kotlinx.coroutines.launch
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Preview
@Composable
fun listUsuarioapp(navController: NavController = rememberNavController()) {
    val api = remember { Api() }
    var usuarios by remember { mutableStateOf(emptyList<Usuario>()) }
    val totalUsuarios by remember { derivedStateOf { usuarios.size } }
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.White,
            darkIcons = true
        )
    }

    LaunchedEffect(Unit) {
        try {
            val result = api.getUsers()
            if (result.isEmpty()) {
                println("La API devolvió una lista vacía.")
            } else {
                println("Usuarios obtenidos correctamente: ${result.size}")
            }
            usuarios = result
        } catch (e: Exception) {
            println("Error obteniendo usuarios: ${e.message}")
        }
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PurpleCustom)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Users: $totalUsuarios",
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }
        items(usuarios) { usuario ->
            itemUsuario(usuario) {
                navController.navigate("detallesUsuario")
            }
        }
    }
}

@Composable
fun itemUsuario(usuario: Usuario, onClick: () -> Unit) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .background(Color.White)
                .clickable { onClick() },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = usuario.image,
                contentDescription = "Imagen del usuario",
                modifier = Modifier
                    .size(60.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = "${usuario.firstName} ${usuario.lastName}",
                    color = Color.Black,
                    fontSize = 16.sp
                )
                Text(
                    text = usuario.company.name,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }
}












