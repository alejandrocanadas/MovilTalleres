package com.example.courseapp

import android.location.Address
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.gson.gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import io.ktor.client.call.body as body

data class UserResponse(
    val users: List<UserApiModel>
)

data class UserApiModel(
    val firstName: String,
    val lastName: String,
    val image: String,
    val company: Company
)


class Api {

    private val client = HttpClient {
        install(ContentNegotiation) {
            gson()
        }
    }

    suspend fun getUsers(): List<Usuario> {
        return withContext(Dispatchers.IO) {
            try {
                val response = client.get("https://dummyjson.com/users")
                val userResponse: UserResponse = response.body()

                println("Datos obtenidos de la API: ${userResponse.users.size}")

                userResponse.users.map { user ->
                    Usuario(
                        firstName = user.firstName,
                        lastName = user.lastName,
                        image = user.image,
                        company = Company(
                            name = user.company.name,
                            department = user.company.department,
                            title = user.company.title
                        )
                    )
                }

            } catch (e: Exception) {
                println("Error en getUsers(): ${e.message}")
                e.printStackTrace()
                emptyList()
            }
        }
    }

}

