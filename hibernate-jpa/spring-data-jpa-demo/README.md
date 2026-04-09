# Spring Data JPA Demo

This module is a command-line Spring Boot demo for core Spring Data JPA operations:

- entity persistence
- batch insert
- paging
- sorting
- DTO projection query
- externalized configuration values

Unlike typical REST demos, logic runs inside `CommandLineRunner` (`DemoApplication#run`) and prints output to the console.

## Tech Stack

- Java 17
- Spring Boot 4.0.5
- Spring Data JPA
- MySQL
- Lombok

## Key Components

- `DemoApplication`
  - Bootstraps sample employee records when repository is empty.
  - Demonstrates `findAll`, `findById`, pagination (`PageRequest`), and sort operations.
  - Reads values from `application.properties` and `application.yml`.
- `entity/Employee`
  - Simple JPA entity with fields: `id`, `name`, `department`, `age`.
- `repository/EmployeeRepo`
  - Extends `JpaRepository<Employee, Integer>`.
  - Includes DTO projection query `findDepartmentAndCount()`.
- `dto/DepartmentCountDto`
  - Record projection for grouped aggregate output.
- `controller/DemoController`
  - Utility component used for startup log messages.

## Query Highlight

`EmployeeRepo#findDepartmentAndCount()`:

- groups employees by department
- returns strongly typed DTO results

```java
@Query("""
        select new com.example.demo.dto.DepartmentCountDto(e.department, count(e))
        from Employee e
        group by e.department
        """)
List<DepartmentCountDto> findDepartmentAndCount();
```

## Configuration

### `application.yml`

- app name: `demo`
- datasource URL: `jdbc:mysql://localhost:3306/demodb?useSSL=false&serverTimezone=UTC`
- `spring.jpa.hibernate.ddl-auto=create`
- SQL logging enabled
- MySQL dialect configured
- extra environment property for startup logging

### `application.properties`

- `demo.greeting= Hello, World!`

Both files are used at runtime; `DemoApplication` reads values from each.

## Run Locally

1. Create MySQL database:

```sql
CREATE DATABASE demodb;
```

2. Update username/password in `application.yml` if needed.
3. Start app:

```bash
mvn spring-boot:run
```

4. Observe console output for:
   - seeded records
   - pagination metadata
   - sorting results
   - department-wise employee counts

## What This Module Teaches

- How `JpaRepository` provides CRUD, pagination, and sorting out-of-the-box.
- How to use JPQL constructor expressions for aggregated DTO responses.
- How to seed and inspect JPA operations quickly without building HTTP endpoints.

## Caveats

- `ddl-auto=create` drops/recreates schema on startup; use with caution.
- This is a learning/demo app and currently lacks API endpoints and business validation.
