# Multi Datasource (Spring Data JPA)

This sample demonstrates how to use **two MySQL datasources in one Spring Boot app** with independent JPA configurations:

- Employee domain -> `javatechie_ds1`
- Department domain -> `javatechie_ds2`

The project is useful when you need to split data ownership across separate schemas/databases while keeping a single API service.

## Tech Stack

- Java 17
- Spring Boot 3.2.4
- Spring Data JPA
- MySQL Connector/J
- Lombok

## Project Structure

- `config/EmployeeDataSourceConfig`  
  Primary datasource, entity manager, and transaction manager for `com.javatechie.entity.employee`.
- `config/DepartmentDataSourceConfig`  
  Secondary datasource setup for `com.javatechie.entity.department`.
- `repository/employee/EmployeeRepository`  
  Employee JPA repository (`ds1`).
- `repository/department/DepartmentRepository`  
  Department JPA repository (`ds2`).
- `service/EmployeeService`  
  Employee CRUD business logic.
- `controller/EmployeeController`  
  REST endpoints under `/employees`.

## Datasource Configuration

Configured in `src/main/resources/application.properties`:

- `spring.datasource.employee.*` -> `jdbc:mysql://localhost:3306/javatechie_ds1`
- `spring.datasource.department.*` -> `jdbc:mysql://localhost:3306/javatechie_ds2`
- `spring.jpa.show-sql=true`
- Hibernate naming strategy set to standard physical naming.

Both datasource configs set:

- `hibernate.hbm2ddl.auto=update`
- `hibernate.dialect=org.hibernate.dialect.MySQLDialect`

## API Endpoints

Base URL: `http://localhost:9292/employees`

- `POST /employees` -> create employee
- `GET /employees` -> list employees
- `GET /employees/{id}` -> get employee by ID
- `PUT /employees/{id}` -> update employee
- `DELETE /employees/{id}` -> delete employee

### Sample Request (Create Employee)

```json
{
  "name": "Alice",
  "dept": "Engineering",
  "salary": 85000,
  "email": "alice@example.com",
  "age": 29
}
```

## Bootstrapped Seed Data

`MultiDatasourceApplication` seeds initial rows at startup when tables are empty:

- 2 employees
- 5 departments

This makes the project runnable without manual inserts.

## Run Locally

1. Create MySQL databases:
   - `javatechie_ds1`
   - `javatechie_ds2`
2. Update DB credentials in `application.properties` if needed.
3. Run:

```bash
mvn spring-boot:run
```

4. Test endpoints using Postman/curl at port `9292`.

## Notes and Caveats

- Credentials are currently hardcoded in properties for local learning only.
- No global exception handler is configured; missing IDs throw `NoSuchElementException`.
- The controller currently focuses on employee APIs only (department has repository/data but no exposed endpoint).