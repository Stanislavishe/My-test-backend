package com.example.database.games

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object Games : Table("games") {
    private val gameId = Games.varchar("game_id", 100)
    private val name = Games.varchar("name", 100)
    private val backdrop = Games.varchar("backdrop", 50).nullable()
    private val logo = Games.varchar("logo", 50)
    private val description = Games.varchar("description", 500)
    private val downloadCount = Games.integer("download_count")
    private val version = Games.varchar("version", 15)
    private val weight = Games.varchar("weight", 10)

    fun insert(gamesDTO: GameDTO) {
        transaction {
            Games.insert {
                it[gameId] = gamesDTO.gameID
                it[name] = gamesDTO.title
                it[description] = gamesDTO.description
                it[version] = gamesDTO.version
                it[weight] = gamesDTO.size
            }
        }
    }

    fun fetchGames(): List<GameDTO> {
        return try {
            transaction {
                Games.selectAll().toList()
                    .map {
                        GameDTO(
                            gameID = it[gameId],
                            title = it[name],
                            description = it[description],
                            size = it[weight],
                            version = it[version]
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }

    }
}