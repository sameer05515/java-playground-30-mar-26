# Hibernate + Spring Data JPA Tutorials

This directory contains multiple focused Spring Data JPA examples.  
Use this file as the entry point to choose a module by learning goal.

## Modules At a Glance

### 1) `spring-data-jpa-demo`

**Goal:** Learn foundational JPA operations from a command-line app.

- startup data seeding with `CommandLineRunner`
- CRUD reads (`findAll`, `findById`)
- pagination and sorting
- DTO projection query (`department -> count`)

Read: [`spring-data-jpa-demo/README.md`](./spring-data-jpa-demo/README.md)

---

### 2) `spring-jpa-qa`

**Goal:** Explore practical JPA query patterns and relationship mapping.

- Employee CRUD endpoints
- method-name query vs JPQL vs native SQL
- aggregate query (`AVG(salary)`)
- pagination and sorting endpoints
- one-to-many mapping with customer/order APIs

Read: [`spring-jpa-qa/README.md`](./spring-jpa-qa/README.md)

---

### 3) `multi-datasource`

**Goal:** Configure and use two MySQL datasources in one Spring Boot service.

- separate datasource properties
- dedicated entity manager + transaction manager per domain
- repository package segregation by datasource
- employee APIs with data persisted in `ds1`
- department data bootstrapped in `ds2`

Read: [`multi-datasource/README.md`](./multi-datasource/README.md)

## Suggested Learning Path

1. Start with `spring-data-jpa-demo` for JPA basics.
2. Move to `spring-jpa-qa` for query techniques and endpoint usage.
3. Finish with `multi-datasource` for advanced configuration patterns.

## Common Prerequisites

- Java 17
- Maven 3.9+
- MySQL 8+
- Local database access with credentials matching each module config

## Notes

- Several modules currently contain hardcoded local DB credentials in properties files.
- `target/` directories are build outputs and should generally not be committed.
