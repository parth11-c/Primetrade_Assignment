# Primetrade Backend Assignment

This project is a full-stack application built as part of the Primetrade Backend Developer (Intern) assignment.

## Tech Stack
- **Backend**: Spring Boot 3.4.2, Java 17, Spring Security, JWT, MongoDB.
- **Frontend**: React 18, Vite, Axios, Lucide React, CSS (Modern Dark Theme).

## Core Features
- User Auth (Register/Login) with JWT.
- Task Management (CRUD).
- Role-Based Access Control.
- API Documentation with Swagger.
- Professional UI with Glassmorphism and Dark Mode.

## Prerequisites
- Java 17+
- Node.js 18+
- MongoDB running locally at `localhost:27017`

## Getting Started

### Backend
1. Navigate to the root directory.
2. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
3. Access API documentation: `http://localhost:8080/swagger-ui.html`

### Frontend
1. Navigate to the `frontend` directory.
2. Install dependencies:
   ```bash
   npm install
   ```
3. Run the development server:
   ```bash
   npm run dev
   ```
4. Access the app: `http://localhost:5173`

## Scalability
See [SCALABILITY.md](./SCALABILITY.md) for detailed notes on scaling this architecture.
