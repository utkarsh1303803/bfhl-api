# BFHL API — Acropolis Campus Hiring

Spring Boot REST API that processes an array of strings and returns categorized results.

## Build

```bash
mvn clean package
```

## Run Locally

```bash
java -jar target/bfhl-api-0.0.1-SNAPSHOT.jar
```

## Sample Request

```bash
curl -X POST http://localhost:8080/bfhl \
  -H "Content-Type: application/json" \
  -d '{"data": ["a", "1", "334", "4", "R", "$"]}'
```

## Expected Response

```json
{
  "is_success": true,
  "user_id": "john_doe_17091999",
  "email": "john@xyz.com",
  "roll_number": "ABCD123",
  "odd_numbers": ["1"],
  "even_numbers": ["334", "4"],
  "alphabets": ["A", "R"],
  "special_characters": ["$"],
  "sum": "339",
  "concat_string": "Ra"
}
```

## Deploy to Render

1. Push this repo to GitHub.
2. Go to [render.com](https://render.com) → New → Web Service.
3. Connect your repo — Render will auto-detect `render.yaml`.
4. Build command: `mvn clean package -DskipTests`
5. Start command: `java -jar target/bfhl-api-0.0.1-SNAPSHOT.jar`

> Before deploying, replace the placeholder constants in `BfhlServiceImpl.java`:
> - `USER_ID` → your name + DOB
> - `EMAIL` → your college email
> - `ROLL_NUMBER` → your actual roll number
