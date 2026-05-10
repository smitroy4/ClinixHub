# ClinixHub — Hospital Management System

A production-grade **Hospital Management System** backend built with **Spring Boot 3.x**, featuring role-based access control, JWT authentication, OAuth2 social login, and a clean layered architecture.

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 4.x.x |
| Security | Spring Security 6, JWT (jjwt 0.12.6), OAuth2 |
| Persistence | Spring Data JPA, Hibernate |
| Database | PostgreSQL |
| Mapping | ModelMapper 3.2 |
| Utilities | Lombok |
| Build Tool | Maven |

---

## Features

- **JWT Authentication** — Stateless login with access token generation and validation
- **OAuth2 Social Login** — Google & GitHub login with automatic user provisioning
- **Role-Based Access Control (RBAC)** — `ADMIN`, `DOCTOR`, `PATIENT` roles with fine-grained permission types
- **Appointment Management** — Patients book appointments; doctors view their schedules
- **Doctor Onboarding** — Admins onboard doctors directly from existing user accounts
- **Patient Management** — Paginated patient listing, profile retrieval, and insurance association
- **Method-Level Security** — `@PreAuthorize` and `@Secured` annotations for service-layer protection
- **Global Exception Handling** — Centralized `@RestControllerAdvice` for consistent error responses
- **Custom JPQL Queries** — Blood group aggregation, date-range filtering, bulk update operations

---

## Project Structure

```
com.smit.projects.clinixhub
├── config/             # App-wide beans (ModelMapper, PasswordEncoder, AuthManager)
├── controller/         # REST controllers (Admin, Auth, Doctor, Patient, Public)
├── dto/                # Request & Response DTOs
├── entity/             # JPA entities (User, Patient, Doctor, Appointment, Insurance, Department)
│   └── type/           # Enums (RoleType, PermissionType, BloodGroupType, AuthProviderType)
├── error/              # GlobalExceptionHandler & ApiError
├── repository/         # Spring Data JPA repositories with custom queries
├── security/           # JWT filter, AuthService, AuthUtil, OAuth2 success handler, WebSecurityConfig
└── service/            # Business logic (Appointment, Doctor, Patient, Insurance)
```

---

## API Endpoints

### Public
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/public/doctors` | List all doctors |

### Auth
| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/v1/auth/signup` | Register a new user |
| `POST` | `/api/v1/auth/login` | Login and receive JWT |

### Admin (Role: ADMIN)
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/admin/patients` | Get all patients (paginated) |
| `POST` | `/api/v1/admin/onBoardNewDoctor` | Onboard a user as a doctor |

### Doctor (Role: DOCTOR / ADMIN)
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/doctors/appointments` | Get own appointments |

### Patient (Authenticated)
| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/v1/patients/appointments` | Book a new appointment |
| `GET` | `/api/v1/patients/profile` | View patient profile |

---

## Security Architecture

```
Request
  └─► JwtAuthFilter (OncePerRequestFilter)
        ├── Extracts & validates Bearer token
        ├── Loads User from DB
        └── Sets Authentication in SecurityContextHolder
              └─► WebSecurityConfig (authorizeHttpRequests)
                    ├── /public/**, /auth/** → permitAll
                    ├── DELETE /admin/** → APPOINTMENT_DELETE or USER_MANAGE permission
                    ├── /admin/** → ROLE_ADMIN
                    ├── /doctors/** → ROLE_DOCTOR or ROLE_ADMIN
                    └── everything else → authenticated
```

**Permission Types:** `PATIENT_READ`, `PATIENT_WRITE`, `APPOINTMENT_READ`, `APPOINTMENT_WRITE`, `APPOINTMENT_DELETE`, `USER_MANAGE`, `REPORT_VIEW`

---

## Entity Relationships

```
User ──────── Patient (1:1, @MapsId)
User ──────── Doctor  (1:1, @MapsId)
Patient ───── Insurance (1:1, owning side)
Patient ───── Appointment (1:N)
Doctor  ───── Appointment (1:N)
Doctor  ───── Department (M:N)
```

---

## Getting Started

### Prerequisites
- Java 21+
- PostgreSQL running locally
- Maven 3.8+

### Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/smit/clinixhub.git
   cd clinixhub
   ```

2. **Configure the database** in `src/main/resources/application.properties`
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/clinixhubDB
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. **Set your JWT secret**
   ```properties
   jwt.secretKey=your_strong_secret_key_minimum_32_chars
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

5. **Access the API** at `http://localhost:8080/api/v1`

---

## OAuth2 Setup (Optional)

To enable Google / GitHub login, add your OAuth2 credentials in `application.yml`:

```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: YOUR_GOOGLE_CLIENT_ID
            client-secret: YOUR_GOOGLE_CLIENT_SECRET
          github:
            client-id: YOUR_GITHUB_CLIENT_ID
            client-secret: YOUR_GITHUB_CLIENT_SECRET
```

---

## Key Design Decisions

- **`@MapsId` on Doctor & Patient** — Shares the primary key with `User`, enforcing a strict 1:1 relationship at the DB level without a separate foreign key column.
- **Permission-based + Role-based hybrid** — Roles group coarse access; permissions allow fine-grained control (e.g., only certain roles can perform DELETE on admin routes).
- **`HandlerExceptionResolver` in JwtAuthFilter** — Routes filter-layer exceptions through Spring MVC's exception handling pipeline so `GlobalExceptionHandler` can produce consistent JSON error responses.
- **Stateless sessions** — `SessionCreationPolicy.STATELESS` ensures no server-side session state; every request is authenticated via JWT.

---

## 👨‍💻Author

**Smit**
- [LinkedIn](https://www.linkedin.com/in/smitroy22/)
- [GitHub](https://github.com/smitroy4)

---

> Built as part of hands-on Spring Boot learning — covering JPA relationships, Spring Security internals, JWT, OAuth2, and clean REST API design.
