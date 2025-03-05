package com.example.courseapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
//manejar la navegación entre pantallas en la app.

@Composable
fun NavigationStack() {
    val navController = rememberNavController()
    // NavHost lo usamos para definir las rutas de la app y cambiar entre pantallas.
    NavHost(navController = navController, startDestination = "listaUsuarios") {
        composable("listaUsuarios") {
            ListaUsuariosScreen(navController)
        }
        //pantalla para mostrar los detalles de un usuario seleccionado
        composable("detallesUsuario") {
            val detalleUsuarioViewModel: DetalleUsuarioViewModel = viewModel()
            DetallesUsuarioScreen(viewModel = detalleUsuarioViewModel)
        }
    }
}

