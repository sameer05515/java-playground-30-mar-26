# Spring JPA Q&A Playground

This project is a **query and API playground** for Spring Data JPA concepts:

- CRUD operations
- derived queries
- JPQL and native queries
- aggregation
- sorting and pagination
- entity relationship mapping (`Customer` -> `Order`)

## Tech Stack

- Java 17
- Spring Boot 3.2.4
- Spring Web
- Spring Data JPA
- MySQL
- Lombok

## Domain Overview

### Employee Module

Entity: `com.javatechie.entity.Employee`

Fields:

- `id`
- `name` (mapped as `EMPNAME`)
- `deptName`
- `salary`
- `emailId`
- `age`

Repository features in `EmployeeRepository`:

- `findBySalaryGreaterThan(double salary)` (method name query)
- `findEmployeeWithJPQL(double salary)` (JPQL query)
- `findEmployeeWithSQL(double salary)` (native SQL query)
- `findByAgeBetween(int min, int max)` (range query)
- `avgSalary()` (aggregate query)

### E-commerce Module

Entities:

- `Customer` (`@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)`)
- `Order` (`@ManyToOne`, table name `orders`)

`OrderFulfillmentService#createOrder()` sets each order's `customer` reference before saving, ensuring a valid parent-child relationship on persist.

## REST APIs

Server port: `9191`  
Base URL: `http://localhost:9191`

### Employee APIs (`/employees`)

- `POST /employees` -> create employee
- `GET /employees` -> list all employees
- `GET /employees/{id}` -> get employee by id
- `PUT /employees/{id}` -> update employee
- `DELETE /employees/{id}` -> delete employee
- `GET /employees/filterBySalary?salary=60000`
- `GET /employees/filterByAgeRange/{minAge}/{maxAge}`
- `GET /employees/averageSalary`
- `GET /employees/sort?field=name`
- `GET /employees/page?offset=0&pageSize=5`
- `GET /employees/pageAndSort?offset=0&pageSize=5&field=salary`

> In service code, the first pagination argument is treated as **page number**, even though controller parameter name is `offset`.

### Order APIs (`/ecom`)

- `POST /ecom/addOrder` -> create customer with nested orders
- `GET /ecom/orderCount` -> raw aggregation rows (`List<Object[]>`)
- `GET /ecom/orderCount/response` -> typed DTO response (`CustomerOrderDTO`)

#### Sample Create Order Payload

```json
{
  "customer": {
    "name": "Prem",
    "email": "prem@example.com",
    "orders": [
      { "id": 101, "name": "Laptop", "qty": 1, "price": 1200.0 },
      { "id": 102, "name": "Mouse", "qty": 2, "price": 25.0 }
    ]
  }
}
```

## Configuration

`src/main/resources/application.properties`:

- datasource: `jdbc:mysql://localhost:3306/javatechie`
- `spring.jpa.hibernate.ddl-auto=update`
- SQL logging enabled
- standard physical naming strategy

`application.yaml` currently contains commented examples only.

## Run Instructions

1. Ensure MySQL is running.
2. Create database:

```sql
CREATE DATABASE javatechie;
```

3. Update credentials in `application.properties` if needed.
4. Start app:

```bash
mvn spring-boot:run
```

## Learning Notes

- Shows the difference between method-name, JPQL, and native queries for the same use case.
- Demonstrates mapping aggregated results to:
  - raw tuple/object arrays
  - custom DTO constructor projections
- Provides practical sorting/pagination endpoints for repository behavior verification.