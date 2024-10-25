package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

fun Application.configureRouting() {
    try {
        routing {
            get("/") {
                call.respondText("Hello World")
            }
        }
    } catch (e: Exception) {

    }
}
