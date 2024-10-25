package com.example.features.games.models

import kotlinx.serialization.Serializable

@Serializable
data class FetchGameRequest(
    val searchQuery: String
)
