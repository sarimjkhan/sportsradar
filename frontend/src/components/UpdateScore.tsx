import React, { useState } from 'react';
import axiosInstance from '../utils/axiosInstance';

interface UpdateScoreProps {
    onScoreUpdated: () => void;  // To notify ScoreBoard
}

const UpdateScore: React.FC<UpdateScoreProps> = ({ onScoreUpdated }) => {
    const [homeTeam, setHomeTeam] = useState('');
    const [awayTeam, setAwayTeam] = useState('');
    const [eventType, setEventType] = useState('goal');
    const [minute, setMinute] = useState(0);
    const [isHomeEvent, setIsHomeEvent] = useState(true);

    const handleUpdateScore = async () => {
        try {
            await axiosInstance.put('/api/scoreboard/update', {
                homeTeam,
                awayTeam,
                eventType,
                minute,
                isHomeEvent
            });
            console.log('Score updated successfully');
            onScoreUpdated();
        } catch (error) {
            console.error('Failed to update the score: ', error);
        }
    };

    return (
        <div>
            <div>
                <label>Home Team:</label>
                <input type="text" value={homeTeam} onChange={(e) => setHomeTeam(e.target.value)} />
            </div>
            <div>
                <label>Away Team:</label>
                <input type="text" value={awayTeam} onChange={(e) => setAwayTeam(e.target.value)} />
            </div>
            <div>
                <label>Event Type:</label>
                <select value={eventType} onChange={(e) => setEventType(e.target.value)}>
                    <option value="goal">goal</option>
                </select>
            </div>
            <div>
                <label>
                    Is Home Team:
                    <input type="checkbox" checked={isHomeEvent} onChange={(e) => setIsHomeEvent(e.target.checked)} />
                </label>
            </div>
            <div>
                <label>Minute:</label>
                <input type="text" value={minute} onChange={(e) => setMinute(parseInt(e.target.value))} />
            </div>
            <button onClick={handleUpdateScore}>Update Score</button>
        </div>
    );
};

export default UpdateScore;
