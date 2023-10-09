import React, { useState } from 'react';
import axiosInstance from '../utils/axiosInstance';

interface UpdateScoreProps {
    onScoreUpdated: () => void;  // To notify ScoreBoard
}

const UpdateScore: React.FC<UpdateScoreProps> = ({ onScoreUpdated }) => {
    const [homeTeam, setHomeTeam] = useState<string>('');
    const [awayTeam, setAwayTeam] = useState<string>('');
    const [homeScore, setHomeScore] = useState<number>(0);
    const [awayScore, setAwayScore] = useState<number>(0);

    const handleScoreUpdate = async () => {
        try {
            await axiosInstance.put('/api/scoreboard/update', {
                homeTeam,
                awayTeam,
                homeScore,
                awayScore,
            });
            onScoreUpdated();
        } catch (error) {
            console.error("Failed to update score:", error);
        }
    };

    return (
        <>
            <div>
                <input type="text" value={homeTeam} onChange={(e) => setHomeTeam(e.target.value)} placeholder='Home Team'/>
                <input type="number" value={homeScore} onChange={e => setHomeScore(Number(e.target.value))} />
            </div>
            <div>
                <input type="text" value={awayTeam} onChange={(e) => setAwayTeam(e.target.value)} placeholder='Away Team'/>
                <input type="number" value={awayScore} onChange={e => setAwayScore(Number(e.target.value))} />
            </div>
            <div><button onClick={handleScoreUpdate}>Update Score</button></div>
        </>
    );
};

export default UpdateScore;
