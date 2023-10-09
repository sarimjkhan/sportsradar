package com.sportsradar.library

import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

@Service
class ScoreBoardService {
    private val matches: MutableList<Match> = mutableListOf();
    private val lock = ReentrantLock();

    fun startMatch(homeTeam: String, awayTeam: String): Match? {
        val existingMatch = matches.find { it.homeTeam == homeTeam && it.awayTeam == awayTeam }
        if(existingMatch != null) {
            throw Exception("Match already exists");
        }

        val match = Match(homeTeam, awayTeam)
        lock.withLock {
            matches.add(match);
        }
        return match;
    }

    fun updateScore(homeTeam: String, awayTeam: String, homeScore: Int, awayScore: Int): Match? {
        val matchToUpdate = matches.find { it.homeTeam == homeTeam && it.awayTeam == awayTeam }
            ?: throw Exception("Match not found")

        matchToUpdate.homeScore = homeScore
        matchToUpdate.awayScore = awayScore

        return matchToUpdate
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