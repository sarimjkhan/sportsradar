import React, { useEffect, useState } from 'react';
import axiosInstance from '../utils/axiosInstance';

interface Match {
    homeTeam: string;
    awayTeam: string;
    homeScore: number;
    awayScore: number;
    startTime: number;
}

const MatchesList: React.FC = () => {
    const [matches, setMatches] = useState<Match[]>([]);

    useEffect(() => {
        const fetchMatches = async () => {
            try {
                const response = await axiosInstance.get('/api/scoreboard/summary');
                setMatches(response.data);
            } catch (error) {
                console.error("Failed to fetch matches:", error);
            }
        };

        fetchMatches();
    }, []);

    const handleMatchDelete = async (homeTeam: string, awayTeam: string) => {
        try {
            const response = await axiosInstance.delete('/api/scoreboard/finish', {data : {
                homeTeam,
                awayTeam
            }})

            setMatches((prevMatches) => 
                prevMatches.filter(match => !(match.homeTeam === homeTeam && match.awayTeam === awayTeam))
            );
        } catch (error) {
            console.error("Failed to delete the match: ", error)
        }
    }

    return (
        <div>
            <h3>Matches List</h3>
            {matches.map((match, index) => (
                <div key={index} style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '10px' }}>
                    <span>{match.homeTeam} {match.homeScore} - {match.awayTeam} {match.awayScore}</span>
                    <button onClick={() => handleMatchDelete(match.homeTeam, match.awayTeam)}>Finish</button>
                </div>
            ))}
        </div>
    );
};

export default MatchesList;
