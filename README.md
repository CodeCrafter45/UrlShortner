# 🔗 Sniply — URL Shortener

A production-ready **URL Shortener** built with **Spring Boot, MySQL, and Docker**. Sniply transforms long URLs into compact, shareable links while providing click analytics through a clean REST API and responsive web interface.

---

##  Features

* 🔗 Generate short URLs instantly
* ↗ Redirect using unique short codes
*  Click analytics dashboard
*  URL validation with meaningful error messages
*  One-click copy to clipboard
*  Interactive API documentation with Swagger UI
*  Unit testing with Mockito
*  Global exception handling
*  One-command setup using Docker Compose

---

## 🛠 Tech Stack

| Technology              | Purpose               |
| ----------------------- | --------------------- |
| Java 17                 | Programming Language  |
| Spring Boot             | Backend Framework     |
| Spring Data JPA         | ORM & Database Access |
| MySQL 8                 | Relational Database   |
| Maven                   | Build Tool            |
| Lombok                  | Boilerplate Reduction |
| Swagger (OpenAPI)       | API Documentation     |
| Docker & Docker Compose | Containerization      |





## Run the Project (Recommended)

### Prerequisites

* Docker Desktop installed
* Docker Engine running

### 1. Clone the repository

```bash
git clone https://github.com/CodeCrafter45/UrlShortner.git
cd UrlShortner
```

### 2. Start the application

```bash
docker compose up --build
```

Docker will automatically:

* Build the Spring Boot application
* Create the MySQL database
* Configure networking
* Start both containers

### 3. Open in your browser

| Service     | URL                                   |
|-------------| ------------------------------------- |
| Application | http://localhost:8080                 |
| Swagger UI  | http://localhost:8080/swagger-ui.html |

---

##  API Endpoints

| Method | Endpoint           | Description              |
| ------ | ------------------ | ------------------------ |
| POST   | `/api/url/shorten` | Create a short URL       |
| GET    | `/r/{shortCode}`   | Redirect to original URL |
| GET    | `/api/url/stats`   | Retrieve click analytics |

---

##  Analytics

Each redirect automatically increments the click count and is displayed in the analytics dashboard.

Example response:

```json
[
  {
    "shortCode": "Ab12Xy",
    "originalUrl": "https://github.com",
    "clickCount": 7
  }
]
```

---

## Project Structure

```text
src
├── controller
├── service
├── repository
├── entity
├── dto
├── exception
├── config
└── resources
```

---

##  Docker Architecture

```text
Browser
   │
   ▼
Spring Boot Container (8080)
   │
   ▼
MySQL Container (3306)
   │
   ▼
Docker Volume (Persistent Data)
```

The application and database run in separate containers connected through a private Docker network.

---

## 🧑‍💻 Local Development (Without Docker)

1. Create a MySQL database named `url_shortener`
2. Update `application.properties`
3. Run the application from your IDE or:

```bash
./mvnw spring-boot:run
```

---

##  Learning Outcomes

This project demonstrates:

* REST API Development
* Layered Architecture
* Spring Data JPA
* DTO Design
* Exception Handling
* Input Validation
* Unit Testing
* Docker Containerization
* Clean Backend Engineering Practices

---

##  Future Improvements

* Custom short aliases
* URL expiration
* QR code generation
* User authentication
* Redis caching
* Rate limiting
* Integration testing
* Cloud deployment (Render/AWS)

---

## 👨‍ Author

**Mahesh Kumbhar**

If you found this project useful, consider giving it a ⭐ on GitHub!
