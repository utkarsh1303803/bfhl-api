# BFHL REST API

A Spring Boot REST API that processes arrays of strings and categorizes them into numbers, alphabets, and special characters. Built with proper validation and error handling.

## What it does

This API takes an array of mixed strings (like `["a", "1", "$"]`) and intelligently separates them into:
- Odd and even numbers
- Alphabetic characters (converted to uppercase)
- Special characters
- Calculates sum of all numbers
- Creates a special concatenated string from alphabets

Perfect for data processing tasks where you need to quickly categorize and analyze mixed input.

## Getting Started

You'll need Java 17 or higher and Maven installed on your machine.

**Build the project:**
```bash
mvn clean package
```

**Run locally:**
```bash
java -jar target/bfhl-api-0.0.1-SNAPSHOT.jar
```

The server will start at `http://localhost:8080`

## How to use

### Process data (POST)

Send your data array to the API and get back organized results.

**Example request:**
```bash
curl -X POST http://localhost:8080/bfhl \
  -H "Content-Type: application/json" \
  -d '{"data": ["a", "1", "334", "4", "R", "$"]}'
```

**What you get back:**
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

### Check status (GET)

Quick health check to see if the API is running.

```bash
curl http://localhost:8080/bfhl
```

Returns: `{"operation_code": 200}`

## Understanding the response

- **is_success** - Did everything work? (true/false)
- **user_id** - Unique identifier for the user
- **email** - Contact email
- **roll_number** - Student/user roll number
- **odd_numbers** - All odd numbers from your input
- **even_numbers** - All even numbers from your input
- **alphabets** - Letters converted to uppercase
- **special_characters** - Everything that's not a number or letter
- **sum** - Total of all numbers added together
- **concat_string** - A special string made from reversing and alternating the case of alphabets

## Testing

Want to make sure everything works? Run the test suite:

```bash
mvn test
```

The project includes 17 tests covering:
- Different types of input (numbers, letters, special chars)
- Edge cases (empty arrays, null values)
- API validation and error responses
- The complex concat_string logic

All tests pass, so you can deploy with confidence!

## Deploying to the cloud

I've deployed this on Railway, but you can use either Railway or Render. Both work great!

### Option 1: Railway (What I used)

Railway is super simple - just connect your GitHub repo and it handles everything automatically.

1. Go to [railway.app](https://railway.app) and sign in with GitHub
2. Click "New Project" → "Deploy from GitHub repo"
3. Select this repository
4. Railway detects it's a Java app and builds it automatically
5. Your API is live! Railway gives you a URL like `https://your-app.up.railway.app`

That's it. Every time you push to GitHub, Railway automatically redeploys.

### Option 2: Render

Render is another solid option with a free tier.

1. Push your code to GitHub (if you haven't already)
2. Go to [render.com](https://render.com) and create a new Web Service
3. Connect your GitHub repository
4. Render will detect the `render.yaml` file and configure everything
5. Click "Create Web Service"

Build command: `mvn clean package -DskipTests`  
Start command: `java -jar target/bfhl-api-0.0.1-SNAPSHOT.jar`

Your API will be live at a URL like `https://your-app.onrender.com`

## Project structure

Here's how the code is organized:

```
bfhl-api/
├── src/main/java/com/acropolis/bfhl/
│   ├── controller/      # API endpoints (GET and POST)
│   ├── service/         # Core logic for processing data
│   ├── dto/            # Request and response models
│   └── exception/      # Error handling
├── src/test/           # All the tests
├── pom.xml            # Maven dependencies
└── README.md          # You are here!
```

## Built with

- **Spring Boot 3.2.5** - The main framework
- **Java 17** - Programming language
- **Maven** - Build and dependency management
- **JUnit 5** - Testing framework
- **Lombok** - Reduces boilerplate code

## Notes

This was built as part of the Acropolis Campus Hiring challenge. The API handles all the requirements including the tricky concat_string logic (reversing alphabets and alternating uppercase/lowercase).

Feel free to use this code for learning or as a reference for your own projects!
