package com.sportsradar.library

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ScoreBoardServiceTest {
    private lateinit var service: ScoreBoardService

    @BeforeEach
    fun setup(){
        service = ScoreBoardService()
    }

    @Test
    fun testStartMatch() {
        val match = service.startMatch("team1", "team2")
        assertEquals("team1", match?.homeTeam)
        assertEquals("team2", match?.awayTeam)
    }

    @Test
    fun testUpdateScore() {
        service.startMatch("team1", "team2")
        val updatedMatch = service.updateScore("team1", "team2", 2, "goal", true, "Ronaldo")
        assertEquals(1, updatedMatch?.homeScore)
        assertEquals(0, updatedMatch?.awayScore)
    }

    @Test
    fun testFinishMatch() {
        service.startMatch("team1", "team2")
        assertTrue(service.finishMatch("team1", "team2"))
    }

    @Test
    fun testMatchSummary() {
        service.startMatch("teamA", "teamB")
        service.updateScore("teamA", "teamB", 2, "goal", true, "Ronaldo")

        val summary = service.getMatchSummary()
        assertEquals(1, summary.size)
        assertEquals("teamA", summary[0].homeTeam)
        assertEquals("teamB", summary[0].awayTeam)
        assertEquals(1, summary[0].homeScore)
        assertEquals(0, summary[0].awayScore)
    }

    @Test
    fun testFinishAndVerifyMatchIsRemoved() {
        service.startMatch("teamX", "teamY")
        service.finishMatch("teamX", "teamY")

        val summary = service.getMatchSummary()
        assertFalse(summary.any { it.homeTeam == "teamX" && it.awayTeam == "teamY" })
    }
}