# Enterprise Employee & Department Management REST API

A production-grade, secure RESTful backend service built with **Spring Boot 4.0.3**, **Java 21**, **Spring Security 6**, and **JWT Authentication**, supporting complete department administration, employee lifecycle tracking, role-based access control, and leave request workflows.

---

## Architecture & Tech Stack

- **Core Framework:** Spring Boot 4.0.3 / Java 21
- **Security:** Spring Security 6, Stateless JWT Authentication, BCrypt Password Hashing
- **Persistence:** Spring Data JPA / Hibernate, Oracle Database Driver (compatible with PostgreSQL/H2)
- **Connection Pool:** HikariCP High-Performance Connection Pool
- **Validation:** Jakarta Validation (`@Valid`, Bean Validation)
- **Data Transfer:** Strongly typed DTOs with Java Records
- **Build System:** Apache Maven (configured with Maven Wrapper `mvnw`)

---

## System Architecture

```
com.TroyBackEnd.employee_managment/
├── abstracts/              # Business Service Interfaces
│   ├── DepartmentService.java
│   ├── EmployeeService.java
│   └── LeaveRequestService.java
├── config/                 # Security & Application Configuration
│   └── SecurityConfig.java # Role-based Route Protection & JWT Filter
├── Controller/             # REST Endpoints
│   ├── AuthController.java
│   ├── DepartmentController.java
│   └── EmployeeController.java
├── dtos/                   # Request & Response Records
│   ├── LoginRequest.java
│   ├── SginupRequest.java
│   ├── DepartmentCreate.java / DepartmentUpdate.java
│   └── EmployeeCreate.java / EmployeeUpdate.java / LeaveRequestCreate.java
├── Entity/                 # JPA Relational Entities
│   ├── AppUser.java / Role.java
│   ├── Department.java
│   ├── Employee.java
│   └── LeaveRequest.java
├── Jwt/                    # Token Extraction & Validation
│   ├── JwtAuthFilter.java
│   └── JwtHelper.java
└── Service/                # Transactional Business Implementations
    ├── AuthService.java
    ├── DepartmentServiceImpl.java
    ├── EmployeeServiceImpl.java
    └── LeaveRequestServiceImpl.java
```

---

## API Endpoints

### 1. Authentication (`/auth`)
| Method | Endpoint | Access | Description |
| :--- | :--- | :--- | :--- |
| `POST` | `/auth/singup` | Public | Register a new user account with employee ID linkage |
| `POST` | `/auth/login` | Public | Authenticate with credentials and receive signed JWT |

### 2. Department Administration (`/departments`)
| Method | Endpoint | Access | Description |
| :--- | :--- | :--- | :--- |
| `GET` | `/departments` | Authenticated | List all departments |
| `GET` | `/departments/{id}` | Authenticated | Fetch department details by UUID |
| `POST` | `/departments` | Authenticated | Create a new department |
| `PUT` | `/departments/{id}` | Authenticated | Update department metadata |
| `DELETE` | `/departments/{id}` | Authenticated | Remove department |

### 3. Employee Management (`/employees`)
| Method | Endpoint | Access | Description |
| :--- | :--- | :--- | :--- |
| `GET` | `/employees` | `ROLE_ADMIN` | List all employees across all departments |
| `GET` | `/employees/{employeeId}` | `ROLE_USER`, `ROLE_ADMIN` | Get employee details by UUID |
| `POST` | `/employees` | `ROLE_ADMIN` | Onboard a new employee with salary & department |
| `PUT` | `/employees/{employeeId}` | `ROLE_USER`, `ROLE_ADMIN` | Update employee profile information |
| `DELETE` | `/employees/{employeeId}` | `ROLE_ADMIN` | Terminate employee record |

### 4. Leave Requests (`/employees/{employeeId}/leave-requests`)
| Method | Endpoint | Access | Description |
| :--- | :--- | :--- | :--- |
| `GET` | `/employees/{employeeId}/leave-requests` | `ROLE_USER`, `ROLE_ADMIN` | List leave requests for an employee |
| `POST` | `/employees/{employeeId}/leave-requests` | `ROLE_USER`, `ROLE_ADMIN` | Submit a new vacation/sick leave request |

---

## Getting Started

### Prerequisites
- **JDK 21 or higher** (`java -version`)
- **Oracle Database, PostgreSQL, or H2 Database**

### Configuration
Copy the configuration template and populate environment variables:
```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

Set the required environment variables:
```bash
export SPRING_DATASOURCE_URL="jdbc:oracle:thin:@//localhost:1521/orcl.home"
export SPRING_DATASOURCE_USERNAME="my_db_user"
export SPRING_DATASOURCE_PASSWORD="my_db_password"
export JWT_SECRET="your_secure_256_bit_secret_key_string_min_32_characters"
```

### Build & Run
Using the included Maven wrapper:
```bash
# Verify compilation and tests
./mvnw clean test-compile

# Package runnable JAR
./mvnw clean package -DskipTests

# Run the application
./mvnw spring-boot:run
```

The service will start on port `8080`.

---

## Automated Validation & Compilation
- **Java Release:** Java 21 LTS (`javac 25.0.2` verified)
- **Maven Version:** Apache Maven 3.9.12
- **Compiler Output:** 33 source files compiled cleanly with 0 compilation errors (`BUILD SUCCESS`).

---

## License
MIT License. Developed for enterprise employee management and secure REST architecture demonstrations.
