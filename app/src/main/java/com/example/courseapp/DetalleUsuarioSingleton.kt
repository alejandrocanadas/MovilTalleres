package com.example.courseapp
// Pusimos este Singleton aquí para guardar el usuario seleccionado y
// poder acceder a el desde la anterior pantalla donde esta toda la lista de los usuarios.

object DetalleUsuarioSingleton {
    private var usuarioSeleccionado: Usuario? = null

    fun setUsuarioSeleccionado(usuario: Usuario) {
        usuarioSeleccionado = usuario
    }

    fun getUsuarioSeleccionado(): Usuario? {
        return usuarioSeleccionado
    }
}
