# BFHL REST API

A Spring Boot REST API that processes arrays of mixed strings and categorizes them into numbers, alphabets, and special characters — with validation, error handling, and a live deployment on Railway.

**Live Base URL:** `https://web-production-3ff22.up.railway.app`

---

## What it does

Send an array like `["a", "1", "$", "R", "334"]` and the API returns:

- Odd and even numbers (separated)
- Alphabets (converted to uppercase)
- Special characters
- Sum of all numbers
- A `concat_string` built by reversing the alphabets and alternating their case

---

## Endpoints

### `POST /bfhl` — Process Data

**Request:**
```bash
curl -X POST https://web-production-3ff22.up.railway.app/bfhl \
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

### `GET /bfhl` — Health Check

```bash
curl https://web-production-3ff22.up.railway.app/bfhl
```

Returns: `{"operation_code": 200}`

---

## Response Fields

| Field | Description |
|---|---|
| `is_success` | `true` if request was processed successfully |
| `user_id` | Unique identifier |
| `email` | Contact email |
| `roll_number` | Roll number |
| `odd_numbers` | Odd numbers extracted from input |
| `even_numbers` | Even numbers extracted from input |
| `alphabets` | Letters from input, converted to uppercase |
| `special_characters` | Everything that's not a number or letter |
| `sum` | Total sum of all numbers |
| `concat_string` | Alphabets reversed with alternating case |

---

## Running Locally

**Prerequisites:** Java 17+, Maven

```bash
# Build
mvn clean package

# Run
java -jar target/bfhl-api-0.0.1-SNAPSHOT.jar
```

Server starts at `http://localhost:8080`

---

## Tests

```bash
mvn test
```

17 tests covering:
- Number, alphabet, and special character categorization
- Edge cases (empty arrays, null values)
- API validation and error responses
- `concat_string` logic

---

## Deployment

Currently deployed on **Railway** (auto-deploys on every push to `main`).

To deploy your own instance:

1. Push code to GitHub
2. Go to [railway.app](https://railway.app) → New Project → Deploy from GitHub repo
3. Select the repository — Railway auto-detects Java and builds it
4. Done. You get a live URL instantly.

Alternatively, **Render** works too — a `render.yaml` is included in the repo.

Build command: `mvn clean package -DskipTests`  
Start command: `java -jar target/bfhl-api-0.0.1-SNAPSHOT.jar`

---

## Project Structure

```
bfhl-api/
├── src/main/java/com/acropolis/bfhl/
│   ├── controller/      # GET and POST endpoints
│   ├── service/         # Core processing logic
│   ├── dto/             # Request and response models
│   └── exception/       # Error handling
├── src/test/            # Test suite
├── pom.xml
└── README.md
```

---

## Tech Stack

- **Java 17**
- **Spring Boot 3.2.5**
- **Maven**
- **JUnit 5**
- **Lombok**

---

## Links

- **GitHub:** [github.com/utkarsh1303803/bfhl-api](https://github.com/utkarsh1303803/bfhl-api)
- **POST endpoint:** `https://web-production-3ff22.up.railway.app/bfhl`
- **GET endpoint:** `https://web-production-3ff22.up.railway.app/bfhl`
