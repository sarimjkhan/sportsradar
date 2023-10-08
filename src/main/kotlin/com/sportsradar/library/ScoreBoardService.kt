package com.sportsradar.library

class ScoreBoardService {
    private val matches: MutableList<Match> = mutableListOf();


    fun startMatch(): Match? {
        return null;
    }

    fun updateScore(): Match? {
        return null;
    }

    fun finishMatch(): Boolean {
        return true;
    }

    fun getMatchSummary(): List<Match> {
        return mutableListOf();
    }
}