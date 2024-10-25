package com.example.database.tournaments

data class TournamentsDTO(
    val tournamentId: String,
    val name: String,
    val status: Int,
    val format: String,
    val prize: String,
    val watchers: Int
)
