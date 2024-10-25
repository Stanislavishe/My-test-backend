package com.example.database.users

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Users : Table("users") {
    private val login = Users.varchar("login", 25)
    private val password = Users.varchar("password", 30)
    private val userName = Users.varchar("username", 25)
    private val email = Users.varchar("email", 30)

    fun insert(userDTO: UserDTO) {
        transaction {
            Users.insert {
                it[login] = userDTO.login
                it[password] = userDTO.password
                it[userName] = userDTO.username
                it[email] = userDTO.email ?: ""
            }
        }
    }

    fun fetchUser(login: String): UserDTO? {
        return try {
            transaction {
                val userModel = Users.select { Users.login eq login }.single()
                UserDTO(
                    login = userModel[Users.login],
                    password = userModel[Users.password],
                    username = userModel[Users.userName],
                    email = userModel[Users.email],
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

    }
}