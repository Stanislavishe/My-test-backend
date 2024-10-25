package com.example

import com.example.features.games.configureGamesRouting
import com.example.features.login.configureLoginRouting
import com.example.features.register.configureRegisterRouting
import com.example.plugins.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.jetbrains.exposed.sql.Database

fun main() {
    Database.connect(
        "jdbc:postgresql://localhost:5432/playzone", driver = "org.postgresql.Driver",
        password = "Stasyanchik5587", user = "postgres"
    )

    embeddedServer(CIO, port = 5452, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureLoginRouting()
    configureRegisterRouting()
    configureGamesRouting()
    configureSerialization()
}
