export default interface Event {
    minute: number;
    eventType: string;
    isHomeEvent: boolean;
    currentHomeScore: number;
    currentAwayScore: number;
}