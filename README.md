📚 Easy-Edu – Microservices-Based Educational Management System
Author: Ruturaj Vasudev

🛠️ Overview
Easy-Edu is a modular, full-stack educational management platform built using Spring Boot microservices architecture. It simplifies and automates key academic operations such as student enrollment, course registration, fee management, and expenditure tracking.

Each service is independently deployable and communicates securely using REST APIs or Feign Clients, allowing the system to scale, recover, and evolve with ease.

🔗 Core Technologies & Features
✅ Spring Boot Microservices – Separate services for students, courses, fees, enrollment, and expenditure.

🧭 Spring Cloud Eureka Server – Centralized service discovery for locating microservices.

🛡️ Spring Cloud Gateway – Acts as the single entry point, managing routing, filtering, and load balancing.

⚙️ Spring Cloud Config Server – Centralized configuration for all microservices to support externalized, version-controlled configs.

🔁 RestTemplate & Feign Clients – Used for service-to-service communication with both flexibility and simplicity.

💥 Circuit Breaker (Resilience4j) – Ensures system resilience and fault tolerance during microservice failure.

🧪 JUnit & Mockito – Achieved over 85% code coverage across services for reliable testing and reduced bugs.

🧮 PostgreSQL & Spring Data JPA – Efficient and scalable data management with ORM for persistence logic.

🔄 Git-based Workflow – Version control and collaborative updates for continuous integration and deployment (CI/CD).

📦 Microservices Included
Main_APP_Eureka_Server – Service registry for discovering and managing microservices.

App_Gateway – API Gateway for routing client requests to respective microservices.

Student_Microservice – Handles student CRUD operations.

Course_Microservice – Manages course creation, updates, and listings.

Enrollment_Microservice – Manages student enrollments and course mappings.

Fee_Microservice – Tracks student payments, dues, and receipts.

Expenditure_Microservice – Monitors institute expenses and generates real-time expense reports.

🚀 Benefits
🔒 Scalable & Secure microservices with service isolation.

💡 Centralized Config and Service Discovery simplify environment management.

🧩 Modular design helps teams work independently on different components.

⚡ Fast recovery from service failure using Circuit Breakers and retry patterns.

