package com.example.courseapp

object DetalleUsuarioSingleton {
    private var usuarioSeleccionado: Usuario? = null

    fun setUsuarioSeleccionado(usuario: Usuario) {
        usuarioSeleccionado = usuario
    }

    fun getUsuarioSeleccionado(): Usuario? {
        return usuarioSeleccionado
    }
}
