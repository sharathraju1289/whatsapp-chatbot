# WhatsApp Chatbot Render Deployment TODO

## Code Updates (Completed after edits)
- [ ] Add PostgreSQL driver to pom.xml
- [ ] Update application-prod.properties for DATABASE_URL

## Deployment Steps
1. Push changes to GitHub repo: `git add . && git commit -m "Prepare for Render deploy" && git push`
2. Login to [render.com](https://render.com), Dashboard → New → Web Service
3. Connect your GitHub repo containing this project
4. Settings: Runtime=Java, Build Command=`./mvnw clean package -DskipTests`, Start Command=`java -jar target/*.jar`
   (render.yaml auto-applies)
5. Create PostgreSQL instance: New → PostgreSQL → Create, name `whatsapp-chatbot-db`
6. Connect DB to Web Service (adds `DATABASE_URL` env var automatically)
7. Environment Variables:
   - `SPRING_PROFILES_ACTIVE`: `prod` (already)
   - `WHATSAPP_VERIFY_TOKEN`: `render-whatsapp-bot-token` (change to secure value)
8. Deploy → View live URL e.g. https://whatsapp-chatbot-xxx.onrender.com
9. Test verify: `curl "https://your-app.onrender.com/webhook?hub.mode=subscribe&hub.verify_token=render-whatsapp-bot-token&hub.challenge=test123"` → should return `test123`
10. [ ] Set WhatsApp Business webhook to your /webhook URL
11. [ ] Test POST webhook payload
