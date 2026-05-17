# E-Commerce Product Catalog REST API

**Course/Subject:** WS101 (Web Systems and Technologies)  
**Student Name:** Longcop, Antonio Jr. N.  
**Institution:** University of Eastern Philippines

---

## Project Description
This project is a backend RESTful API built using the Spring Boot framework to manage an e-commerce product catalog. It handles standard CRUD (Create, Read, Update, Delete) operations and implements basic server-side data validation.

---

## Architecture Components
The application follows a standard layered architecture:
* **Controller Layer:** Maps HTTP endpoints and handles request/response routing.
* **Service Layer:** Manages core business logic and state management.
* **Model Layer:** Defines the `Product` blueprint object with data fields and validations.

---

## API Endpoints Matrix

| HTTP Method | Endpoint URI | Description | Expected Status |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/v1/products` | Retrieve all 10 mock catalog products | `200 OK` |
| **GET** | `/api/v1/products/{id}` | Retrieve a single product by its unique ID | `200 OK` |
| **POST** | `/api/v1/products` | Add a new product (e.g., Nike Shoes) with validation | `201 Created` |
| **DELETE** | `/api/v1/products/{id}` | Delete a specific product from memory | `204 No Content` |

---

## How To Run and Test The Project

1. Open the project folder inside **IntelliJ IDEA**.
2. Wait for Maven to download dependencies.
3. Locate and run `EcommerceApiApplication.java` using the green Play button.
4. Open **Postman** (or a web browser) to test the endpoints at `http://localhost:8080`.