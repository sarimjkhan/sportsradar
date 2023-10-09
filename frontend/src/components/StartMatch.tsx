import React, { useState } from 'react';
import axiosInstance from '../utils/axiosInstance';

interface StartMatchProps {
    onMatchStarted: () => void;  // To notify ScoreBoard Component
}

const StartMatch: React.FC<StartMatchProps> = ({ onMatchStarted }) => {
    const [homeTeam, setHomeTeam] = useState<string>('');
    const [awayTeam, setAwayTeam] = useState<string>('');

    const handleMatchStart = async () => {
        try {
            await axiosInstance.post('/api/scoreboard/start', {
                homeTeam,
                awayTeam
            });
            onMatchStarted();
        } catch (error) {
            console.error("Failed to start match:", error);
        }
    };

    return (
        <div>
            <input type="text" value={homeTeam} onChange={e => setHomeTeam(e.target.value)} placeholder="Home Team" />
            <input type="text" value={awayTeam} onChange={e => setAwayTeam(e.target.value)} placeholder="Away Team" />
            <button onClick={handleMatchStart}>Start Match</button>
        </div>
    );
};

export default StartMatch;
