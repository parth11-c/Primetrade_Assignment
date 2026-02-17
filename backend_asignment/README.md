# Primetrade Assignment - Backend API

A secure, RESTful Task Management API built with **Spring Boot 3.4.2**, **MongoDB**, and **JWT Authentication**.

## 🚀 Features

- ✅ **User Authentication & Authorization** (JWT-based)
- ✅ **Role-Based Access Control** (USER/ADMIN)
- ✅ **CRUD Operations for Tasks**
- ✅ **MongoDB Integration**
- ✅ **Spring Security**
- ✅ **Input Validation**
- ✅ **Global Exception Handling**
- ✅ **Swagger/OpenAPI Documentation**

---

## 📋 Table of Contents

- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [API Endpoints](#api-endpoints)
- [Request/Response Examples](#requestresponse-examples)
- [Security](#security)
- [Project Structure](#project-structure)

---

## 🛠️ Tech Stack

| Technology | Version | Purpose |
|-----------|---------|---------|
| **Java** | 17 | Programming Language |
| **Spring Boot** | 3.4.2 | Framework |
| **Spring Security** | 6.x | Authentication & Authorization |
| **Spring Data MongoDB** | 4.x | Database Integration |
| **MongoDB** | Latest | NoSQL Database |
| **JWT (JJWT)** | 0.11.5 | Token-based Authentication |
| **Maven** | 3.x | Build Tool |
| **Springdoc OpenAPI** | 2.3.0 | API Documentation |

---

## ✅ Prerequisites

Before running this application, ensure you have:

- **Java 17** or higher installed
- **Maven 3.6+** installed
- **MongoDB** running locally on port `27017`
- **Git** (for cloning the repository)

---

## 📦 Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/parth11-c/Primetrade_Assignment.git
cd Primetrade_Assignment/backend_asignment
```

### 2. Install Dependencies

```bash
mvn clean install
```

### 3. Configure MongoDB

Ensure MongoDB is running on your local machine:

```bash
# Start MongoDB (macOS with Homebrew)
brew services start mongodb-community

# Or start manually
mongod --dbpath /path/to/your/data/directory
```

The application will automatically create a database named `primetrade`.

---

## ⚙️ Configuration

The application configuration is located in `src/main/resources/application.properties`:

```properties
# Application Name
spring.application.name=primetrade assignment

# MongoDB Configuration
spring.data.mongodb.uri=mongodb://localhost:27017/primetrade

# JWT Security Configuration
application.security.jwt.secret-key=404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970
application.security.jwt.expiration=86400000  # 24 hours in milliseconds

# Swagger/OpenAPI Configuration
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

### Environment Variables (Optional)

For production, consider using environment variables:

```bash
export MONGODB_URI=mongodb://localhost:27017/primetrade
export JWT_SECRET_KEY=your-secret-key-here
export JWT_EXPIRATION=86400000
```

---

## 🏃 Running the Application

### Option 1: Using Maven

```bash
mvn spring-boot:run
```

### Option 2: Using Java

```bash
mvn clean package
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

The application will start on **http://localhost:8080**

---

## 📚 API Documentation

Once the application is running, access the interactive API documentation:

- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **OpenAPI JSON**: [http://localhost:8080/api-docs](http://localhost:8080/api-docs)

---

## 🔗 API Endpoints

### Authentication Endpoints

| Method | Endpoint | Description | Authentication |
|--------|----------|-------------|----------------|
| `POST` | `/api/v1/auth/register` | Register a new user | ❌ Public |
| `POST` | `/api/v1/auth/login` | Login and get JWT token | ❌ Public |

### Task Management Endpoints

| Method | Endpoint | Description | Authentication |
|--------|----------|-------------|----------------|
| `POST` | `/api/v1/tasks` | Create a new task | ✅ Required |
| `GET` | `/api/v1/tasks` | Get all tasks for authenticated user | ✅ Required |
| `PUT` | `/api/v1/tasks/{id}` | Update a task by ID | ✅ Required |
| `DELETE` | `/api/v1/tasks/{id}` | Delete a task by ID | ✅ Required |

---

## 📝 Request/Response Examples

### 1. User Registration

**Endpoint:** `POST /api/v1/auth/register`

**Request Body:**
```json
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "password": "SecurePassword123"
}
```

**Response:** `200 OK`
```json
"User registered successfully"
```

---

### 2. User Login

**Endpoint:** `POST /api/v1/auth/login`

**Request Body:**
```json
{
  "email": "john.doe@example.com",
  "password": "SecurePassword123"
}
```

**Response:** `200 OK`
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "email": "john.doe@example.com",
  "role": "USER"
}
```

---

### 3. Create Task

**Endpoint:** `POST /api/v1/tasks`

**Headers:**
```
Authorization: Bearer <your-jwt-token>
Content-Type: application/json
```

**Request Body:**
```json
{
  "title": "Complete Project Documentation",
  "description": "Write comprehensive README and API documentation",
  "status": "PENDING"
}
```

**Response:** `200 OK`
```json
{
  "id": "65f8a3b2c4d5e6f7a8b9c0d1",
  "title": "Complete Project Documentation",
  "description": "Write comprehensive README and API documentation",
  "status": "PENDING",
  "userId": "65f8a1b2c3d4e5f6a7b8c9d0",
  "createdAt": "2024-02-17T14:30:00"
}
```

---

### 4. Get All Tasks

**Endpoint:** `GET /api/v1/tasks`

**Headers:**
```
Authorization: Bearer <your-jwt-token>
```

**Response:** `200 OK`
```json
[
  {
    "id": "65f8a3b2c4d5e6f7a8b9c0d1",
    "title": "Complete Project Documentation",
    "description": "Write comprehensive README and API documentation",
    "status": "PENDING",
    "userId": "65f8a1b2c3d4e5f6a7b8c9d0",
    "createdAt": "2024-02-17T14:30:00"
  },
  {
    "id": "65f8a3b2c4d5e6f7a8b9c0d2",
    "title": "Review Code",
    "description": "Perform code review for pull request #123",
    "status": "COMPLETED",
    "userId": "65f8a1b2c3d4e5f6a7b8c9d0",
    "createdAt": "2024-02-16T10:15:00"
  }
]
```

---

### 5. Update Task

**Endpoint:** `PUT /api/v1/tasks/{id}`

**Headers:**
```
Authorization: Bearer <your-jwt-token>
Content-Type: application/json
```

**Request Body:**
```json
{
  "title": "Complete Project Documentation",
  "description": "Write comprehensive README and API documentation - Updated",
  "status": "COMPLETED"
}
```

**Response:** `200 OK`
```json
{
  "id": "65f8a3b2c4d5e6f7a8b9c0d1",
  "title": "Complete Project Documentation",
  "description": "Write comprehensive README and API documentation - Updated",
  "status": "COMPLETED",
  "userId": "65f8a1b2c3d4e5f6a7b8c9d0",
  "createdAt": "2024-02-17T14:30:00"
}
```

---

### 6. Delete Task

**Endpoint:** `DELETE /api/v1/tasks/{id}`

**Headers:**
```
Authorization: Bearer <your-jwt-token>
```

**Response:** `200 OK`
```json
"Task deleted successfully"
```

---

## 🔒 Security

### JWT Authentication Flow

1. **Register/Login**: User registers or logs in with credentials
2. **Token Generation**: Server generates a JWT token valid for 24 hours
3. **Token Usage**: Client includes token in `Authorization` header for protected endpoints
4. **Token Validation**: Server validates token on each request

### Security Features

- ✅ **Password Hashing**: BCrypt encryption for passwords
- ✅ **JWT Tokens**: Stateless authentication
- ✅ **Role-Based Access**: USER and ADMIN roles
- ✅ **CORS Configuration**: Configured for cross-origin requests
- ✅ **Input Validation**: Request validation using Jakarta Validation
- ✅ **Global Exception Handling**: Centralized error handling

### Protected Endpoints

All `/api/v1/tasks/**` endpoints require a valid JWT token in the Authorization header:

```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

---

## 📁 Project Structure

```
backend_asignment/
├── src/
│   ├── main/
│   │   ├── java/com/parth/primetrade/
│   │   │   ├── config/              # Security & Application Configuration
│   │   │   │   ├── ApplicationConfig.java
│   │   │   │   ├── JwtAuthenticationFilter.java
│   │   │   │   └── SecurityConfig.java
│   │   │   ├── controller/          # REST Controllers
│   │   │   │   ├── TController.java (Task Controller)
│   │   │   │   └── UController.java (Auth Controller)
│   │   │   ├── dto/                 # Data Transfer Objects
│   │   │   │   ├── AuthResponse.java
│   │   │   │   ├── LoginRequest.java
│   │   │   │   ├── RegisterRequest.java
│   │   │   │   ├── TaskRequest.java
│   │   │   │   └── TaskResponse.java
│   │   │   ├── Entity/              # MongoDB Entities
│   │   │   │   ├── Task.java
│   │   │   │   ├── User.java
│   │   │   │   └── Urole.java
│   │   │   ├── exception/           # Exception Handling
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   ├── Repo/                # MongoDB Repositories
│   │   │   │   ├── TaskRepo.java
│   │   │   │   └── UserRepo.java
│   │   │   ├── Service/             # Business Logic
│   │   │   │   ├── AuthService.java
│   │   │   │   ├── JwtService.java
│   │   │   │   └── TService.java
│   │   │   ├── util/                # Utility Classes
│   │   │   │   └── SecurityUtils.java
│   │   │   └── PrimetradeAssignmentApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/                        # Unit Tests
├── pom.xml                          # Maven Dependencies
└── README.md                        # This file
```

---

## 🧪 Testing

### Run Tests

```bash
mvn test
```

### Test Coverage

The project includes:
- Unit tests for services
- Integration tests for controllers
- Security tests for authentication

---

## 🐛 Error Handling

The API uses standard HTTP status codes:

| Status Code | Description |
|-------------|-------------|
| `200` | Success |
| `400` | Bad Request (Invalid input) |
| `401` | Unauthorized (Invalid/missing token) |
| `403` | Forbidden (Insufficient permissions) |
| `404` | Not Found |
| `500` | Internal Server Error |

**Example Error Response:**
```json
{
  "timestamp": "2024-02-17T14:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Email already exists",
  "path": "/api/v1/auth/register"
}
```

---

## 🚀 Deployment

### Building for Production

```bash
mvn clean package -DskipTests
```

This creates a JAR file in the `target/` directory.

### Running in Production

```bash
java -jar target/demo-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

---

## 📄 License

This project is created as an assignment for Primetrade.

---

## 👨‍💻 Author

**Parth Bhende**
- GitHub: [@parth11-c](https://github.com/parth11-c)

---

## 📞 Support

For issues or questions, please create an issue in the GitHub repository.

---

## 🔄 Future Enhancements

- [ ] Add pagination for task listing
- [ ] Implement task filtering and sorting
- [ ] Add task categories/tags
- [ ] Email verification for registration
- [ ] Password reset functionality
- [ ] Task sharing between users
- [ ] Task due dates and reminders
- [ ] File attachments for tasks
- [ ] Activity logs and audit trails
- [ ] Docker containerization

---

**Happy Coding! 🎉**
