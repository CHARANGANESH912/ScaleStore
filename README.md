# 🚀 ScaleStore — High-Concurrency E-Commerce Backend

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.2-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![Redis](https://img.shields.io/badge/Redis-Caching-red)
![Docker](https://img.shields.io/badge/Docker-Containerization-blue)
![GitHub Actions](https://img.shields.io/badge/CI%2FCD-GitHub%20Actions-brightgreen)

A production-ready Spring Boot backend for an e-commerce platform demonstrating secure authentication, role-based authorization, distributed caching, concurrency-safe order processing, containerization, automated testing, and CI/CD.

---

# 📌 Features

- 🔐 JWT Authentication
- 👥 Role-Based Authorization (Admin / Customer)
- 📦 Product Management
- 🛒 Order Management
- ⚡ Redis Distributed Caching
- 🔄 Concurrency-Safe Inventory Updates
- 🐘 PostgreSQL Database
- 🛡️ Global Exception Handling
- 📑 Swagger/OpenAPI Documentation
- 🐳 Docker & Docker Compose Support
- 🧪 Unit Testing with JUnit & Mockito
- 🌐 Controller Testing with MockMvc
- ✅ GitHub Actions CI/CD Pipeline

---

# 🛠️ Tech Stack

## Backend
- Java 17
- Spring Boot 3
- Spring Security 6
- Spring Data JPA
- Hibernate ORM
- Spring Validation
- Spring Cache
- Springdoc OpenAPI (Swagger)

## Database
- PostgreSQL
- H2 Database (Development)

## Caching
- Redis

## Testing
- JUnit 5
- Mockito
- MockMvc

## DevOps
- Docker
- Docker Compose
- GitHub Actions
- Maven

---

# 🏗️ Architecture

```text
                Client
                   │
                   ▼
        Spring Security (JWT)
                   │
                   ▼
           REST Controllers
                   │
                   ▼
             Service Layer
            │             │
            ▼             ▼
      Redis Cache    PostgreSQL
```

The application follows a layered architecture:

- Controller Layer
- Service Layer
- Repository Layer
- Database Layer

---

# 🔐 Authentication

Authentication is implemented using JWT tokens.

Workflow:

1. Register a new user
2. Login using email & password
3. Receive JWT token
4. Authorize using:

```
Authorization: Bearer <JWT_TOKEN>
```

5. Access protected endpoints

Passwords are encrypted using BCrypt.

---

# ⚡ Redis Caching

Redis is used to improve application performance.

Implemented using:

- `@Cacheable`
- `@CacheEvict`

Benefits:

- Faster product retrieval
- Reduced database load
- Improved response time

---

# 🔄 Concurrency Control

To prevent overselling during simultaneous purchases, the application uses database locking.

Implemented using:

```java
@Lock(LockModeType.PESSIMISTIC_WRITE)
```

This ensures:

- No race conditions
- Safe stock updates
- Transactional consistency

---

# 📡 REST API

## Authentication

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/auth/register` | Register User |
| POST | `/auth/login` | Login & Generate JWT |

---

## Products

| Method | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/products` | Get All Products |
| GET | `/api/products/{id}` | Get Product By ID |
| POST | `/api/admin/products` | Add Product (Admin) |

---

## Orders

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/api/orders` | Place Order |
| GET | `/api/orders` | Get User Orders |

---

# 📘 Swagger Documentation

After starting the application:

```
http://localhost:8080/swagger-ui/index.html
```

Swagger provides:

- API Documentation
- Request Examples
- Response Models
- Interactive API Testing

---

# 🐳 Docker

Run the complete application:

```bash
docker compose up --build
```

This starts:

- Spring Boot
- PostgreSQL
- Redis

---

# 🚀 Running Locally

Clone the repository:

```bash
git clone https://github.com/<your-username>/ScaleStore.git
```

Go to project directory:

```bash
cd ScaleStore
```

Run:

```bash
./mvnw spring-boot:run
```

or on Windows:

```bash
mvnw.cmd spring-boot:run
```

---

# 🧪 Testing

Run all tests:

```bash
./mvnw test
```

Tests include:

- Unit Tests
- Service Layer Tests
- Controller Tests (MockMvc)

---

# 🔄 Continuous Integration

GitHub Actions automatically:

- Builds the project
- Executes all tests
- Verifies every push and pull request

---

# 📂 Project Structure

```text
ScaleStore
│
├── Authentication
│     ├── JWT
│     └── Spring Security
│
├── Product Module
├── Order Module
├── Redis Cache
├── PostgreSQL
├── Docker
├── Swagger
├── Testing
│     ├── JUnit
│     ├── Mockito
│     └── MockMvc
│
└── GitHub Actions CI
```

---

# 📈 Future Improvements

- Kafka Event Streaming
- Elasticsearch
- Prometheus & Grafana Monitoring
- Kubernetes Deployment
- Distributed Tracing
- Email Notifications
- Payment Gateway Integration

---

# 👨‍💻 Author

**Kalevaru Charan Ganesh**

Backend Java Developer

B.Tech – Computer Science & Engineering

B. V. Raju Institute of Technology, Narsapur

---

# 📄 License

This project is licensed under the MIT License.

---

## ⭐ If you found this project useful, consider giving it a star!
