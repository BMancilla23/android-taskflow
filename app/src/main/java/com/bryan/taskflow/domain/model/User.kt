package com.bryan.taskflow.domain.model

data class User(
    val id: Long,
    val fullName: String,
    val email: String,
    val password: String,
    val created: Long = System.currentTimeMillis()
)