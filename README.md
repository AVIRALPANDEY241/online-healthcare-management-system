# online-healthcare-management-system
# Online Healthcare Management System

> Simple, modular Java-based healthcare appointment & record management system (console / web-ready). Designed to demonstrate OOP (inheritance, polymorphism, interfaces), collections & generics, multithreading/synchronization, JDBC database connectivity, and exception handling.

---

## Table of Contents

* [Project Overview](#project-overview)
* [Features](#features)
* [Architecture & Tech Stack](#architecture--tech-stack)
* [Project Structure](#project-structure)
* [Database Schema](#database-schema)
* [Setup & Run (Local)](#setup--run-local)
* [API Endpoints (if using REST module)](#api-endpoints-if-using-rest-module)
* [Sample Data](#sample-data)
* [Testing](#testing)
* [Extending / Contributing](#extending--contributing)
* [Known limitations](#known-limitations)
* [License](#license)

---

## Project Overview

This project provides a basic Online Healthcare Management System to manage doctors, patients, and appointments. It's intentionally modular so you can run it as a console application, expand to a web API, or connect a GUI later.

Use cases covered:

* Register doctors and patients
* Book, cancel, and list appointments with conflict checks
* Basic role-based operations (admin/doctor/patient) via services
* Thread-safe booking with synchronization to avoid race conditions

---

## Features

* CRUD for Doctor, Patient, Appointment
* Appointment conflict detection (by doctor and timeslot)
* Exception handling with custom exceptions (e.g., `BookingException`)
* JDBC-based persistence (MySQL / PostgreSQL) via DAO layer
* Thread-safety on booking operations
* Unit-testable service layer

---

## Architecture & Tech Stack

* Language: Java 11+ (works with 8+, but recommended 11+)
* Build: Maven
* Database: MySQL / PostgreSQL (JDBC)
* Testing: JUnit 5
* Patterns: DAO, Service, Model, DTO (optional)

---

## Project Structure

```
OnlineHealthcareManagementSystem/
├─ pom.xml
├─ README.md
└─ src/main/java/com/healthcare/
   ├─ Main.java
   ├─ model/ (Doctor.java, Patient.java, Appointment.java)
   ├─ dao/ (GenericDao.java, DoctorDao.java, PatientDao.java, AppointmentDao.java)
   ├─ dao/impl/ (JdbcDoctorDao.java, JdbcPatientDao.java, JdbcAppointmentDao.java)
   ├─ service/ (DoctorService.java, PatientService.java, BookingService.java)
   ├─ service/impl/ (DoctorServiceImpl.java, PatientServiceImpl.java, BookingServiceImpl.java)
   ├─ exception/ (BookingException.java)
   └─ util/ (DBConnection.java)
```

Notes:

* `util/DBConnection.java` contains DB URL, user, and password (prefer environment variables for production).
* `BookingServiceImpl` is where synchronization for booking should live (e.g., `synchronized` blocks or `ReentrantLock`).

---

## Database Schema

Example (MySQL) tables:

```sql
CREATE TABLE doctor (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  specialization VARCHAR(100),
  phone VARCHAR(20),
  email VARCHAR(100)
);

CREATE TABLE patient (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  dob DATE,
  phone VARCHAR(20),
  email VARCHAR(100)
);

CREATE TABLE appointment (
  id INT AUTO_INCREMENT PRIMARY KEY,
  doctor_id INT NOT NULL,
  patient_id INT NOT NULL,
  start_time DATETIME NOT NULL,
  end_time DATETIME NOT NULL,
  status VARCHAR(20) DEFAULT 'SCHEDULED',
  notes TEXT,
  FOREIGN KEY (doctor_id) REFERENCES doctor(id),
  FOREIGN KEY (patient_id) REFERENCES patient(id)
);
```

Indexing recommendation:

* Add an index on `(doctor_id, start_time, end_time)` for fast conflict checks.

---

## Setup & Run (Local)

### Prerequisites

* Java 11+ installed
* Maven installed
* MySQL / PostgreSQL installed and a database created

### Steps

1. Clone the repo:

```bash
git clone <repo-url>
cd OnlineHealthcareManagementSystem
```

2. Configure database connection in `src/main/java/com/healthcare/util/DBConnection.java` or set environment variables (recommended):

```java
// example fields
private static final String URL = System.getenv("DB_URL");
private static final String USER = System.getenv("DB_USER");
private static final String PASS = System.getenv("DB_PASS");
```

3. Create the tables using the SQL from **Database Schema**.

4. Build and run (console app):

```bash
mvn clean package
# run with java -cp target/your-artifact.jar com.healthcare.Main
mvn exec:java -Dexec.mainClass="com.healthcare.Main"
```

If you provide a web module (Spring Boot), run via `mvn spring-boot:run` instead.

---

## API Endpoints (if using REST module)

> Include these if the project exposes HTTP endpoints (Spring Boot example)

* `GET  /api/doctors` — list doctors
* `GET  /api/doctors/{id}` — get doctor
* `POST /api/doctors` — create doctor
* `GET  /api/patients` — list patients
* `POST /api/appointments` — create appointment (payload includes doctorId, patientId, startTime, endTime)
* `GET  /api/appointments?doctorId=&date=` — filter appointments
* `DELETE /api/appointments/{id}` — cancel appointment

Authentication/authorization is out-of-scope for the basic project but recommended for extensions.

---

## Sample Data

```sql
INSERT INTO doctor (name, specialization, phone, email) VALUES
('Dr. A Kumar','Cardiology','+91-9999999999','dr.a@example.com');

INSERT INTO patient (name, dob, phone, email) VALUES
('Ramesh Sharma','1990-05-10','+91-8888888888','ramesh@example.com');

INSERT INTO appointment (doctor_id, patient_id, start_time, end_time, status) VALUES
(1, 1, '2025-11-30 10:00:00', '2025-11-30 10:30:00','SCHEDULED');
```

---

## Testing

* Unit tests: add JUnit tests for service layer (mock DAOs)
* Integration: run with a local test DB and use real DAOs

Example test ideas:

* Concurrent booking attempts for the same doctor/time should result in only one success.
* CRUD operations for doctor/patient
* Appointment listing with filters

---

## Extending / Contributing

* Add authentication (JWT + Spring Security) for REST module
* Create a React/Vue frontend that consumes the REST API
* Add notifications (email/SMS) using a provider
* Add richer patient records (prescriptions, diagnoses, billing)

Contributions: open an issue, fork the repo, and submit a pull request.

---

## Known limitations

* No authentication by default
* Minimal input validation in early versions
* Limited audit/history for appointments
* No external notification system included

---

## License

This project is released under the MIT License — see `LICENSE` file.

---

If you want, I can:

* Generate a `docker-compose.yml` to run the DB and app together,
* Add a Spring Boot module with REST controllers,
* Create sample Postman collection or curl examples.

Tell me which of these you'd like next.
