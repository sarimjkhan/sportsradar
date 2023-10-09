package com.sportsradar.library

data class Match(
    val homeTeam: String,
    val awayTeam: String,
    var homeScore: Int = 0,
    var awayScore: Int = 0,
    val startTime: Long = System.currentTimeMillis()
)