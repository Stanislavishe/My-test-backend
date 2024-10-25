package com.example.features.register

import com.example.cache.InMemoryCache
import com.example.cache.TokenCache
import com.example.database.tokens.TokenDTO
import com.example.database.tokens.Tokens
import com.example.database.users.UserDTO
import com.example.database.users.Users
import com.example.utils.isValidEmail
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import java.util.*

class RegisterController(private val call: ApplicationCall) {

    suspend fun registerNewUser() {
        val receive = call.receive<RegisterReceiveRemote>()
        if(!receive.email.isValidEmail()){
            call.respond(HttpStatusCode.BadRequest, "Email is not valid")
        }

        val userDTO = Users.fetchUser(receive.login)

        if (userDTO != null) {
            call.respond(HttpStatusCode.Conflict, "Login is already registered")
        } else {
            val randomUID = UUID.randomUUID().toString()

            try {
                Users.insert(
                    UserDTO(
                        login = receive.login,
                        password = receive.password,
                        email = receive.email,
                        username = ""
                    )
                )
            } catch (e: ExposedSQLException){
                call.respond(HttpStatusCode.Conflict, "Login is already registered")
            }

            Tokens.insert(
                TokenDTO(
                    id = UUID.randomUUID().toString(),
                    login = receive.login,
                    token = randomUID
                )
            )

            call.respond(RegisterResponseRemote(token = randomUID))
        }
    }
}