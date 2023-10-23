package com.sportsradar.library

import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

@Service
class ScoreBoardService {
    private val matches: MutableList<Match> = mutableListOf()

    private val lock = ReentrantLock()

    fun startMatch(homeTeam: String, awayTeam: String): Match? {
        val existingMatch = matches.find { it.homeTeam == homeTeam && it.awayTeam == awayTeam }
        if(existingMatch != null) {
            throw Exception("Match already exists")
        }

        val match = Match(homeTeam, awayTeam)
        lock.withLock {
            matches.add(match)
        }
        return match
    }

    fun updateScore(homeTeam: String, awayTeam: String, minute: Int, eventType: String, isHomeEvent: Boolean): Match? {
        val match = matches.find { it.homeTeam == homeTeam && it.awayTeam == awayTeam }

        match?.let {
            if (eventType == "goal") {
                if (isHomeEvent) it.homeScore++ else it.awayScore++
            }

            val event = Event(homeTeam, awayTeam, minute, eventType, isHomeEvent, it.homeScore, it.awayScore)
            println(event);
            it.events.add(event)
        }

        return match
    }

    fun finishMatch(homeTeam: String, awayTeam: String): Boolean {
        var isRemoved = false
        lock.withLock {
            isRemoved = matches.removeIf {
                it.homeTeam == homeTeam && it.awayTeam == awayTeam
            }
        }
        if(!isRemoved) {
            throw Exception("Match not found")
        }
        return isRemoved
    }

    fun getMatchSummary(): List<Match> {
        // Sort by total score first
        return matches.sortedWith(
            compareByDescending<Match>{ it.homeScore + it.awayScore}
                .thenByDescending { it.startTime }
        )
    }
}