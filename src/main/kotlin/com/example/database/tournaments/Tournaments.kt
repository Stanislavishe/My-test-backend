package com.example.database.tournaments

import org.jetbrains.exposed.sql.Table

object Tournaments: Table("tournaments") {
    private val tournamentId = Tournaments.varchar("tournament_id", 50)
    private val name = Tournaments.varchar("name", 100)
    private val status = Tournaments.integer("status")
    private val format = Tournaments.varchar("format", 20)
    private val prize = Tournaments.varchar("prize", 25)
    private val watchers = Tournaments.integer("watchers")

    fun insert(tournamentsDTO: TournamentsDTO){

    }
}