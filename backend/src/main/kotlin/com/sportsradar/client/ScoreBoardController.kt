package com.sportsradar.client

import com.sportsradar.library.Match
import com.sportsradar.library.ScoreBoardService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("/api/scoreboard")
class ScoreBoardController(private val scoreBoardService: ScoreBoardService) {

    @PostMapping("/start")
    fun startMatch(@RequestBody match: Match): ResponseEntity<Match>{
        val startedMatch = scoreBoardService.startMatch(match.homeTeam, match.awayTeam)
        return ResponseEntity.ok(startedMatch)
    }

    @PutMapping("/update")
    fun updateScore(@RequestBody match: Match): ResponseEntity<Match>{
        val updatedMatch = scoreBoardService.updateScore(match.homeTeam, match.awayTeam, match.homeScore, match.awayScore)
        return ResponseEntity.ok(updatedMatch)
    }

    @DeleteMapping("/finish")
    fun finishMatch(@RequestBody match: Match): ResponseEntity<Void>{
        scoreBoardService.finishMatch(match.homeTeam, match.awayTeam)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/summary")
    fun getMatchSummary(): ResponseEntity<List<Match>> {
        val summary = scoreBoardService.getMatchSummary()
        return ResponseEntity.ok(summary)
    }
}