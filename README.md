Markdown
# 🚀 ScaleStore: High-Concurrency Backend

**ScaleStore** is a professional-grade e-commerce backend built with **Spring Boot 3**. It is specifically designed to handle high-traffic retail scenarios using distributed caching and optimistic concurrency control.

---

## 🛠️ Tech Stack
* **Language:** Java 17
* **Framework:** Spring Boot 3.2.5
* **Security:** Spring Security with Stateless JWT (JSON Web Tokens)
* **Database:** H2 (In-Memory) / Spring Data JPA
* **Caching:** Redis (Distributed Caching via Redis Cloud)
* **Frontend:** Vanilla JavaScript, HTML5, CSS3 (Fetch API)

---

## ✨ Key Technical Features

### 1. Stateless Authentication (JWT)
The system uses **JWT** for secure, stateless communication. Passwords are never stored in plain text; they are encrypted using the **BCrypt** hashing algorithm. Every protected request is intercepted by a custom `JwtFilter` to validate the user's identity before hitting the controllers.

### 2. Distributed Caching (Redis)
To ensure high performance and low latency, the application integrates with **Redis Cloud**. Product catalogs are cached to minimize database hits. The system utilizes `@Cacheable` for lightning-fast reads and `@CacheEvict` to maintain data consistency whenever stock or product details are updated.

### 3. Concurrency Control (Optimistic Locking)
To prevent "Race Conditions" (e.g., two users buying the last item at the same time), the project implements **Optimistic Locking** using the JPA `@Version` annotation. This ensures data integrity and prevents over-selling without the performance cost of heavy database-level locks.

### 4. Global Exception Handling
A centralized `@ControllerAdvice` handles all application errors. This ensures that the frontend receives clean, consistent JSON error messages instead of standard Spring "WhiteLabel" error pages, improving the overall User Experience.

---

## 🚀 Getting Started

### Prerequisites
* JDK 17
* Maven
* IntelliJ IDEA

### Installation & Run
1. **Clone the project** to your local machine.
2. **Redis Setup:** Ensure your Redis Cloud credentials (host, port, and password) are correctly set in `src/main/resources/application.properties`.
3. **Run Application:** Open the project in IntelliJ and run `ScaleStoreApplication.java` or use the terminal:
   ```bash
   mvn spring-boot:run
Access UI: Open your browser and go to: http://localhost:8080/

📡 API Endpoints
Method	Endpoint	Description	Auth Required
POST	/api/auth/signup	Register a new user	No
POST	/api/auth/login	Login and receive JWT	No
GET	/api/products	Fetch all products (Cached)	Yes
POST	/api/products	Add a new product	Yes
POST	/api/products/{id}/purchase	Purchase item (Concurrency safe)	Yes
👨‍💻 Author
Kalevaru Charan Ganesh
BTech Final Year Student
B. V. Raju Institute of Technology, Narsapur

