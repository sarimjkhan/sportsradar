import React, { useEffect, useState } from 'react';
import axiosInstance from '../utils/axiosInstance';
import Match from '../models/Match';
import Event from '../models/Event'; // Import the Event model

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

    const renderEvents = (events: Event[]) => {
        return events.map((event, index) => (
            <div key={index}>
                {event.eventType} scored after {event.minute} minutes: 
                {event.currentHomeScore} - {event.currentAwayScore} 
            </div>
        ));
    }

    return (
        <div>
            <h3>Matches List</h3>
            {matches.map((match, index) => (
                <div key={index} style={{ marginBottom: '20px' }}>
                    <h4>{match.homeTeam} vs {match.awayTeam}</h4>
                    <div>
                        {renderEvents(match.events)}
                    </div>
                </div>
            ))}
        </div>
    );
};

export default MatchesList;
