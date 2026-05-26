# BFHL REST API

A production-ready Spring Boot REST API for processing and categorizing string arrays with comprehensive validation and error handling.

## Features

- **POST /bfhl** - Process data arrays and return categorized results
- **GET /bfhl** - Health check endpoint returning operation code
- Input validation with detailed error messages
- Comprehensive test coverage (17 passing tests)
- Ready for cloud deployment (Railway/Render)

## Quick Start

### Prerequisites
- Java 17+
- Maven 3.9+

### Build
```bash
mvn clean package
```

### Run
```bash
java -jar target/bfhl-api-0.0.1-SNAPSHOT.jar
```

Server starts on `http://localhost:8080`

## API Endpoints

### POST /bfhl
Processes an array of strings and returns categorized data.

**Request:**
```bash
curl -X POST http://localhost:8080/bfhl \
  -H "Content-Type: application/json" \
  -d '{"data": ["a", "1", "334", "4", "R", "$"]}'
```

**Response:**
```json
{
  "is_success": true,
  "user_id": "utkarsh_singh_07072005",
  "email": "utkarshsingh230400@acropolis.in",
  "roll_number": "0827AL231137",
  "odd_numbers": ["1"],
  "even_numbers": ["334", "4"],
  "alphabets": ["A", "R"],
  "special_characters": ["$"],
  "sum": "339",
  "concat_string": "Ra"
}
```

### GET /bfhl
Returns operation status code.

**Request:**
```bash
curl http://localhost:8080/bfhl
```

**Response:**
```json
{
  "operation_code": 200
}
```

## Response Fields

| Field | Description |
|-------|-------------|
| `is_success` | Boolean indicating request success |
| `user_id` | User identifier in format `name_ddmmyyyy` |
| `email` | User email address |
| `roll_number` | User roll number |
| `odd_numbers` | Array of odd numeric strings |
| `even_numbers` | Array of even numeric strings |
| `alphabets` | Array of alphabetic strings (uppercase) |
| `special_characters` | Array of non-alphanumeric strings |
| `sum` | Sum of all numeric values as string |
| `concat_string` | Reversed alphabets with alternating case |

## Testing

Run all tests:
```bash
mvn test
```

Test coverage includes:
- Unit tests for service logic
- Integration tests for API endpoints
- Validation and error handling tests

## Deployment

### Railway (Recommended)
1. Push to GitHub
2. Connect repository to Railway
3. Railway auto-deploys from `main` branch

### Render
1. Push to GitHub
2. Create new Web Service on Render
3. Connect repository
4. Render uses `render.yaml` for configuration

## Project Structure

```
bfhl-api/
├── src/main/java/com/acropolis/bfhl/
│   ├── controller/      # REST endpoints
│   ├── service/         # Business logic
│   ├── dto/            # Request/Response models
│   └── exception/      # Error handling
├── src/test/           # Test suites
├── pom.xml            # Maven configuration
└── README.md          # Documentation
```

## Technology Stack

- **Framework:** Spring Boot 3.2.5
- **Language:** Java 17
- **Build Tool:** Maven
- **Testing:** JUnit 5, MockMvc
- **Validation:** Jakarta Bean Validation

## License

MIT License - Built for Acropolis Campus Hiring Challenge
