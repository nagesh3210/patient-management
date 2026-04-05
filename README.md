🏥 Patient Management System

A scalable microservices-based healthcare application designed to manage patient data, appointments, and analytics efficiently. This system enables seamless communication between services using modern backend technologies and event-driven architecture.

🚀 Features
👤 Patient Registration & Management
📅 Appointment Scheduling
🔐 Authentication & Authorization
📊 Analytics & Reporting Service
🌐 API Gateway Routing
⚡ Event-driven communication using Kafka
🔄 Microservices architecture with service discovery
🧾 RESTful APIs with validation
🏗️ Architecture

This project follows a Microservices Architecture, where each service is independently deployable and communicates via REST APIs and messaging systems.

Core Services:
API Gateway – Entry point for all client requests
Auth Service – Handles authentication and user management
Patient Service – Manages patient records
Analytics Service – Processes and analyzes patient data
Service Registry (Eureka) – Enables service discovery

💡 The system ensures loose coupling and scalability using distributed design principles.

🛠️ Tech Stack
Backend
Java (Spring Boot)
Spring Cloud (Gateway, Eureka)
Spring Data JPA
Hibernate
Messaging & Streaming
Apache Kafka
Database
MySQL / PostgreSQL
DevOps & Tools
Docker
Maven
LocalStack (for AWS simulation)
