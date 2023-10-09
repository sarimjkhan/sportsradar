import {useState} from 'react'
import MatchesList from './MatchesList';
import StartMatch from './StartMatch';
import UpdateScore from './UpdateScore';

const ScoreBoard = () => {
    //To refresh using the key of MatchesList component
    const [refreshKey, setRefreshKey] = useState<number>(0);

    const handleRefresh = () => setRefreshKey(prevKey => prevKey + 1);

    return (
        <div>
            <h1>ScoreBoard</h1>
            
            <label>Start a match</label>
            <StartMatch onMatchStarted={handleRefresh} />
            
            <hr />
            <label>Update the score</label>
            <UpdateScore onScoreUpdated={handleRefresh} />

            <hr />
            <MatchesList key={refreshKey} />
        </div>
    );
};

export default ScoreBoard;
