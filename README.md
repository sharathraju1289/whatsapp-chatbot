# WhatsApp Chatbot Backend Simulation

Spring Boot REST API simulating WhatsApp Business API webhook for simple chatbot.

## Features ✅
- **/webhook POST**: Receives WhatsApp JSON payloads, extracts text messages
- **Predefined Replies**:
  | Input | Output |
  |-------|--------|
  | hi/hello/hey | Hello! 👋 How can I help you today? |
  | bye/goodbye/see you | Goodbye! 👋 Have a great day! |
  | help | I can respond to: hi/hello, bye/goodbye, help |
  | any other | I received: '{input}'. Try 'hi' or 'bye'! 😊
- **Logging**: Console (DEBUG) + DB (H2 local, PostgreSQL prod)
- **Verification**: GET /webhook?hub.mode=subscribe&hub.challenge=...&hub.verify_token=...
- **CORS**: Enabled for all origins
- **Health**: /actuator/health

## Quick Start (Local)
```bash
./mvnw clean spring-boot:run
```
- H2 Console: http://localhost:8080/h2-console (JDBC: `jdbc:h2:mem:testdb`)
- Test DB: `SELECT * FROM message_logs;`

## Test Webhook
```bash
curl -X POST http://localhost:8080/webhook \
  -H "Content-Type: application/json" \
  -d '{
    \"entry\": [{
      \"changes\": [{
        \"value\": {
          \"messages\": [{
            \"id\": \"wamid.123\",
            \"from\": \"1234567890\",
            \"type\": \"text\",
            \"text\": {\"body\": {\"text\": \"Hi\"}}
          }]
        }
      }]
    }]
  }'
```

**Expected Response**:
```json
{
  \"messaging_product\": \"whatsapp\",
  \"contacts\": [{\"delivery\": \"1234567890\"}],
  \"messages\": [{\"id\": \"wamid.123\", \"to\": \"1234567890\", \"text\": {\"body\": \"Hello! 👋 How can I help you today?\"}}]
}
```
Check logs/DB for entry.

## Deploy to Render (Free)
1. Push to GitHub repo
2. render.com → New Service → Web → Connect GitHub repo
3. Auto-detects `render.yaml` (Maven build → JAR → prod profile)
4. Test deployed URL: `https://your-app.onrender.com/webhook`

## Architecture
```
WhatsApp Webhook JSON → Controller → Log (Console/DB) → Simple Reply Logic → WhatsApp Response JSON
```
- Models: Full WhatsApp payload parsing
- DB Schema: `message_logs(id, phone, message, response, timestamp)`

## Screenshots
*(Add your local/deployed test screenshots here)*

**Demo Video**: Local run + curl tests + Render deploy + logs/DB verification (2-3 mins).

Built for assignment submission.
