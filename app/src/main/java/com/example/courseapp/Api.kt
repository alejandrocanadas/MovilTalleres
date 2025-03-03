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
    val id: Int,
    val firstName: String,
    val lastName: String,
    val maidenName: String,
    val age: Int,
    val gender: String,
    val email: String,
    val phone: String,
    val username: String,
    val password: String,
    val birthDate: String,
    val image: String,
    val bloodGroup: String,
    val height: Double,
    val weight: Double,
    val eyeColor: String,
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

                        id = user.id,
                            firstName = user.firstName,
                            lastName = user.lastName,
                            maidenName = user.maidenName,
                            age = user.age,
                            gender = user.gender,
                            email = user.email,
                            phone = user.phone,
                            username = user.username,
                            password = user.password,
                            birthDate = user.birthDate,
                            image = user.image,
                            bloodGroup = user.bloodGroup,
                            height = user.height,
                            weight = user.weight,
                            eyeColor = user.eyeColor,
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

