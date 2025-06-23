# EduMatrix Backend - Spring Boot Application

## Overview
This is the backend service for the EduMatrix Course Management System, built with Spring Boot and H2 in-memory database.

## Features
- RESTful API for course and course instance management
- H2 in-memory database with JPA/Hibernate
- Comprehensive validation and error handling
- CORS configuration for frontend integration
- Dependency validation for course prerequisites
- Sample data initialization

## Technology Stack
- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **H2 Database**
- **Maven**

## API Endpoints

### Course Management
- `GET /api/courses` - Get all courses
- `GET /api/courses/{courseId}` - Get specific course
- `POST /api/courses` - Create new course
- `DELETE /api/courses/{courseId}` - Delete course

### Course Instance Management
- `GET /api/instances` - Get all instances (with optional year/semester filters)
- `GET /api/instances/{year}/{semester}/{courseId}` - Get specific instance
- `POST /api/instances` - Create new instance
- `DELETE /api/instances/{year}/{semester}/{courseId}` - Delete instance

## Running the Application

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Steps
1. Navigate to the backend directory:
   ```bash
   cd backend
   ```

2. Build the application:
   ```bash
   mvn clean compile
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. The application will start on `http://localhost:8080`

### H2 Database Console
Access the H2 console at: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:edumatrix`
- Username: `sa`
- Password: `password`

## Sample Data
The application initializes with sample data:
- 4 courses (CS101, CS201, CS301, MATH101)
- Prerequisites relationships
- 4 course instances for 2025

## Validation Rules
- Course IDs must follow pattern: 2-4 letters + 3 digits (e.g., CS101)
- Prerequisites must exist before creating dependent courses
- Cannot delete courses that are prerequisites for other courses
- Cannot delete courses with active 🐳 Docker Setup

## Folder Structure

project/
├── backend/              # Spring Boot backend with Dockerfile
├── frontend/             # React frontend with Dockerfile
├── docker-compose.yaml   # Root-level Docker orchestrator
- Course instances must be unique per course/year/semester combination

## Error Handling
- Comprehensive exception handling with appropriate HTTP status codes


- Validation error messages
- Custom exceptions for business logic violations

## CORS Configuration
Configured to allow requests from:
- `http://localhost:5173` (Vite dev server)
- `http://localhost:3000` (Alternative dev server)
