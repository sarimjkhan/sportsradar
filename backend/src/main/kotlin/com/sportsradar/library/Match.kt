package com.sportsradar.library

data class Event(
    val homeTeam: String,
    val awayTeam: String,
    val minute: Int,
    val eventType: String,
    val isHomeEvent: Boolean,
    val currentHomeScore: Int = 0,
    val currentAwayScore: Int = 0,
    val playerInitials: String
)

data class Match(
    val homeTeam: String,
    val awayTeam: String,
    var homeScore: Int = 0,
    var awayScore: Int = 0,
    val events: MutableList<Event> = mutableListOf(),
    val startTime: Long = System.currentTimeMillis()
)