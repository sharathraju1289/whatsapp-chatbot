# WhatsApp Chatbot TODO - COMPLETED ✅

## Completed Steps:
- [x] Create /webhook POST endpoint for WhatsApp JSON payloads
- [x] Implement predefined replies: Hi/Hello/Hey → 'Hello! 👋...', Bye/Goodbye → 'Goodbye! 👋...', Help, default fallback
- [x] Log incoming messages to console (SLF4J DEBUG) and DB (MessageLog → H2 local/PostgreSQL prod)
- [x] Handle WhatsApp webhook verification (GET /webhook)
- [x] Model full WhatsApp payload/response structures
- [x] Configure local H2 DB + prod PostgreSQL ready (application-prod.properties)
- [x] Prepare Render deployment (render.yaml)

## Deployment & Demo:
1. Local: `./mvnw spring-boot:run` → Test at http://localhost:8080/webhook
2. Render: Create service at render.com → Connect GitHub repo → Deploy (auto-builds JAR, uses prod profile)
3. Test: Use curl/Postman with WhatsApp JSON sample
4. Screenshots/Video: Local logs/DB, deployed endpoint responses

## Sample Curl Test:
```
curl -X POST http://localhost:8080/webhook \
  -H "Content-Type: application/json" \
  -d '{
    "entry": [{
      "changes": [{
        "value": {
          "messages": [{
            "id": "test123",
            "from": "1234567890",
            "type": "text",
            "text": {"body": {"text": "Hi"}}
          }]
        }
      }]
    }]
  }'
```
Expected: Logs saved, response with "Hello! 👋..."

**Ready for submission: GitHub repo + 2-3 min demo video (local/deployed tests).**


## Connect Render to VS Code [IN PROGRESS ✅ Started]
- [x] Create .vscode/tasks.json for Maven build, local run, Render deploy
- [x] Update README.md with VS Code/CLI instructions
- [x] Install Render CLI via npm & brew: `npm i -g render` + `brew install render` (v2.15.0)
- [ ] Login: `render login`
- [ ] Deploy: `Ctrl+Shift+P` > "Tasks: Run Task" > "Deploy to Render" (or terminal `render deploy`)
- [ ] Test health/webhook on deployed URL
- [ ] Deploy: `Ctrl+Shift+P` > "Tasks: Run Task" > "Deploy to Render" (or terminal `render deploy`)
- [ ] Test health/webhook on deployed URL
