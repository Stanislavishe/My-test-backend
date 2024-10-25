package com.example.database.games

import com.example.features.games.models.CreateGameRequest
import com.example.features.games.models.CreateGameResponse
import com.example.features.games.models.GameResponse
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class GameDTO(
    val gameID: String,
    val title: String,
    val description: String,
    val version: String,
    val size: String
)

fun CreateGameRequest.mapToGameDTO(): GameDTO =
    GameDTO(
        gameID = UUID.randomUUID().toString(),
        title = title,
        description = description,
        version = version,
        size = size
    )

fun GameDTO.mapToCreateGameResponse(): CreateGameResponse =
    CreateGameResponse(
        gameID = gameID,
        title = title,
        description = description,
        version = version,
        size = size
    )

fun GameDTO.mapToGameResponse(): GameResponse =
    GameResponse(
        gameId = gameID,
        title = title,
        description = description,
        version = version,
        size = size
    )

