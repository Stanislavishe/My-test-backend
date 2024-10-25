package com.example.features.login

import com.example.cache.InMemoryCache
import com.example.cache.TokenCache
import com.example.database.tokens.TokenDTO
import com.example.database.tokens.Tokens
import com.example.database.users.UserDTO
import com.example.database.users.Users
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

class LoginController(private val call: ApplicationCall) {

    suspend fun performLogin(){
        val receive = call.receive<LoginReceiveRemote>()
        val userDTO = Users.fetchUser(receive.login)
        println("receive -> $receive, dto -> $userDTO")

        if(userDTO == null){
            call.respond(HttpStatusCode.BadRequest, "User not found")
        } else {
            if(userDTO.password == receive.password){
                val randomUID = UUID.randomUUID().toString()
                Tokens.insert(
                    TokenDTO(
                        id = UUID.randomUUID().toString(),
                        login = receive.login,
                        token = randomUID
                    )
                )
                call.respond(LoginResponseRemote(randomUID))
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid password")
            }
        }
        call.respond(HttpStatusCode.BadRequest)
    }
}