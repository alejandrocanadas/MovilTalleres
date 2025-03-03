package com.example.courseapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationStack() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "listaUsuarios") {
        composable("listaUsuarios") {
            ListaUsuariosScreen(navController)
        }
        composable("detallesUsuario") {
            val detalleUsuarioViewModel: DetalleUsuarioViewModel = viewModel()
            DetallesUsuarioScreen(viewModel = detalleUsuarioViewModel)
        }
    }
}
