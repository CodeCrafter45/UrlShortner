# 🔗 URL Shortener

A scalable URL Shortener built with **Spring Boot** that transforms long URLs into compact, shareable links. The project focuses on clean architecture, RESTful API design, and backend best practices using Java and Spring Boot.

## 📖 Overview

This application allows users to generate short URLs from long URLs and redirect back to the original destination. It is being developed as a backend engineering project to explore real-world concepts such as REST APIs, database design, URL encoding, validation, and scalable application architecture.

##  Features

- Shorten long URLs
- Redirect using short URLs
- URL validation
- Persistent storage with MySQL
- RESTful API design
- Layered architecture (Controller → Service → Repository)
- Global exception handling
- Input validation
- Clean and maintainable code

### Planned Enhancements

- Custom short aliases
- Click analytics
- URL expiration
- User authentication
- Rate limiting
- Caching with Redis
- Docker support
- Unit and integration testing

## 🛠 Tech Stack

| Technology | Purpose |
|------------|---------|
| Java 17 | Programming Language |
| Spring Boot | Backend Framework |
| Spring Data JPA | Database Access |
| MySQL | Relational Database |
| Maven | Build Tool |
| Lombok | Boilerplate Reduction |

## 📁 Project Structure

```
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

## 🚀 Getting Started

1. Clone the repository
2. Configure MySQL
3. Update `application.properties`
4. Run the application using Maven or your IDE

##  Learning Objectives

This project is being built to strengthen understanding of:

- Spring Boot fundamentals
- REST API development
- Spring Data JPA
- MySQL integration
- Backend architecture
- Exception handling
- Validation
- Git & GitHub workflow

## 📌 Project Status

🚧 Currently under active development.

New features and improvements are being added incrementally.

##  Contributing

Contributions, suggestions, and feedback are always welcome.

## 📄 License

This project is licensed under the MIT License.
