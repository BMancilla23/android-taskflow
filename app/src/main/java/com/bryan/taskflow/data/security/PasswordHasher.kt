package com.bryan.taskflow.data.security

import org.mindrot.jbcrypt.BCrypt

object PasswordHasher {
    fun hash(password: String): String {
        return BCrypt.hashpw(
            password,
            BCrypt.gensalt()
        )
    }

    fun verify(password: String, passwordHash: String): Boolean{
        return BCrypt.checkpw(
            password,
            passwordHash
        )
    }
}