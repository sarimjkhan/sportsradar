package com.sportsradar.library

import java.time.LocalDateTime;

data class Match(
    val homeTeam: String,
    val awayTeam: String,
    var homeScore: Int = 0,
    var awayScore: Int = 0,
    val startTime: LocalDateTime = LocalDateTime.now()
)