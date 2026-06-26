# 🏥 Hospital Appointment Booking System

A **production-ready RESTful API** built with Java Spring Boot for managing hospital appointments between patients and doctors.

## 📋 Features

- ✅ Patient registration and management
- ✅ Doctor management with department association
- ✅ Department management
- ✅ Appointment booking with status workflow
- ✅ Global exception handling with meaningful error responses
- ✅ Input validation
- ✅ Layered architecture (Controller → Service → Repository)
- ✅ DTO pattern for clean API design

## 🛠️ Technologies Used

| Technology | Version | Purpose |
|---|---|---|
| Java | 17 | Core language |
| Spring Boot | 3.x | Application framework |
| Spring Data JPA | 3.x | Database abstraction |
| MySQL | 8.0 | Relational database |
| Maven | 3.x | Build tool |
| Lombok | Latest | Reduce boilerplate |
| Jakarta Validation | Latest | Input validation |

## 🗄️ Database Design

```
Department (1) ────── (Many) Doctor (Many) ────── (1) Appointment (Many) ────── (1) Patient
```

### Entities:
- **Patient** → id, name, email, phone, dateOfBirth, gender
- **Department** → id, name, description
- **Doctor** → id, name, specialization, email, phone, department
- **Appointment** → id, patient, doctor, date, time, status, notes

## 🚀 REST API Endpoints

### Patient APIs
| Method | Endpoint | Description |
|---|---|---|
| POST | /api/patients | Register patient |
| GET | /api/patients | Get all patients |
| GET | /api/patients/{id} | Get patient by ID |
| PUT | /api/patients/{id} | Update patient |
| DELETE | /api/patients/{id} | Delete patient |

### Department APIs
| Method | Endpoint | Description |
|---|---|---|
| POST | /api/departments | Create department |
| GET | /api/departments | Get all departments |
| GET | /api/departments/{id} | Get department by ID |
| PUT | /api/departments/{id} | Update department |
| DELETE | /api/departments/{id} | Delete department |

### Doctor APIs
| Method | Endpoint | Description |
|---|---|---|
| POST | /api/doctors | Register doctor |
| GET | /api/doctors | Get all doctors |
| GET | /api/doctors/{id} | Get doctor by ID |
| GET | /api/doctors/department/{id} | Get doctors by department |
| PUT | /api/doctors/{id} | Update doctor |
| DELETE | /api/doctors/{id} | Delete doctor |

### Appointment APIs
| Method | Endpoint | Description |
|---|---|---|
| POST | /api/appointments | Book appointment |
| GET | /api/appointments | Get all appointments |
| GET | /api/appointments/{id} | Get appointment by ID |
| GET | /api/appointments/patient/{id} | Get by patient |
| GET | /api/appointments/doctor/{id} | Get by doctor |
| PATCH | /api/appointments/{id}/status | Update status |
| PATCH | /api/appointments/{id}/cancel | Cancel appointment |

## ⚙️ How to Run Locally

### Prerequisites
- Java 17+
- MySQL 8.0+
- Maven 3.x

### Steps

1. **Clone the repository**
```bash
git clone https://github.com/rashitha-r/hospital-appointment-system.git
cd hospital-appointment-system
```

2. **Create MySQL database**
```sql
CREATE DATABASE hospital_db;
```

3. **Configure application.properties**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hospital_db
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

4. **Run the application**
```bash
mvn spring-boot:run
```

5. **Access the API**
```
http://localhost:8080
```

## 📁 Project Structure

```
src/main/java/com/hospital/hospital_appointment_system/
├── controller/         # REST API controllers
├── service/            # Business logic
│   └── impl/          # Service implementations
├── repository/         # Database layer
├── entity/             # JPA entities
├── dto/                # Data Transfer Objects
│   ├── request/       # Input DTOs
│   └── response/      # Output DTOs
└── exception/          # Custom exceptions
```

## 🔄 Appointment Status Workflow

```
PENDING → CONFIRMED → COMPLETED
    └──→ CANCELLED
```

## 👩‍💻 Author

**Rashitha R**
- GitHub: [@rashitha-r](https://github.com/rashitha-r)
- Internship: i5 Technologies