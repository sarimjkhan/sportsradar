import Event from "./Event";

export default interface Match {
    homeTeam: string;
    awayTeam: string;
    homeScore: number;
    awayScore: number;
    events: Event[];
    startTime: number;
}