# SportRadar ScoreBoard System

### Configuration & Running (Monorepo contains both backend and frontend)

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/sarimjkhan/sportsradar.git
This repository contains both the backend (Spring Boot Application) and frontend (React Application).

# **Backend (Spring Boot Application)**

### Prerequisites

- **Java JDK 11 or higher**:
  - Ensure you have JDK 11 or higher installed. If not, [download and install from here](https://adoptopenjdk.net/).

- **Maven**:
  - Ensure Maven is installed. If not, [download and install from here](https://maven.apache.org/download.cgi).

**Add JDK to JAVA_HOME depending on the operating system**

- **Steps to run**:
   ```bash
   cd sportsradar/backend
   ./mvn clean install
   ./mvn spring-boot:run
   
   The server should start at localhost:8080

   Alternatively, we can open the backend folder in intellij idea IDE

- **API endpoints**
   
   **[POST] '/api/scoreboard/start'**
   - Payload: {"homeTeam": "chelsea", "awayTeam": "arsenal"}
   - Response: A match object (homeTeam, awayTeam, homeScore, awayScore, startTime)

   **[PUT] '/api/scoreboard/update'**
   - Payload: {"homeTeam": "chelsea", "awayTeam": "arsenal", "homeScore": 2, "awayScore": 1}
   - Response: A updated match object (homeTeam, awayTeam, homeScore, awayScore, startTime)

   **[DELETE] '/api/scoreboard/finish'**
   - Payload: {"homeTeam": "chelsea", "awayTeam": "arsenal"}
   - Response: Match deleted

   **[GET] '/api/scoreboard/summary'**
   - Payload: -
   - Response: List of match objects sorted (homeTeam, awayTeam, homeScore, awayScore, startTime)

-  **To run the tests**
   -./mvnw test

# **Frontend (React Application)**

### Prerequisites

- **node**:
  - Ensure you have node installed. If not, [download and install from here](https://nodejs.org/en).

- **Steps to run**:
   ```bash
   cd sportsradar/frontend
   npm install
   npm run dev
   
   The web interface should start at http://localhost:5173

### Improvements
- Both of the projects have big margins of improvements.
- The project structures can be made more scalable
- Error handling can be stricter in the frontend and backend projects
- The packaging can be done using Docker to avoid the environmental and configurational issues
- More unit tests can be added.
- Unit tests can be added to frontend