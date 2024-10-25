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
                it[gameId] = gamesDTO.gameId
                it[name] = gamesDTO.name
                it[backdrop] = gamesDTO.backdrop
                it[logo] = gamesDTO.logo
                it[description] = gamesDTO.description
                it[downloadCount] = gamesDTO.downloadCount
                it[version] = gamesDTO.version
                it[weight] = gamesDTO.weight
            }
        }
    }

    fun fetchGames(): List<GameDTO> {
        return try {
            transaction {
                Games.selectAll().toList()
                    .map {
                        GameDTO(
                            gameId = it[gameId],
                            name = it[name],
                            backdrop = it[backdrop],
                            logo = it[logo],
                            description = it[description],
                            weight = it[weight],
                            version = it[version],
                            downloadCount = it[downloadCount]
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }

    }
}