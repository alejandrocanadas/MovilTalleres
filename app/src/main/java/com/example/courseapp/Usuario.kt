package com.example.courseapp

data class Usuario(
    val firstName: String,
    val lastName: String,
    val image: String,
    val company: Company
)

data class Company(
    val name: String,
    val department: String,
    val title: String
)