# Swagger Playground

This module demonstrates OpenAPI documentation generation for a Spring Boot TODO CRUD API using springdoc.

## Module overview

- Project: `swagger-demo`
- Stack: Spring Boot WebMVC + springdoc OpenAPI UI
- Storage: in-memory map (no external database)
- Default app port: `8080`

## What it demonstrates

- API documentation with Swagger UI
- OpenAPI metadata customization via `SwaggerConfig`
- CRUD endpoints documented using `@Operation` and `@Tag`

## Run

```bash
cd swagger-demo
./mvnw spring-boot:run
```

## Access documentation

- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## API endpoints

Base path: `/todos`

- `GET /todos` - list all TODOs
- `GET /todos/{id}` - get TODO by id
- `POST /todos` - create TODO
- `PUT /todos/{id}` - update TODO
- `DELETE /todos/{id}` - delete TODO

## Example requests

Create:

```bash
curl -X POST http://localhost:8080/todos -H "Content-Type: application/json" -d "{\"title\":\"Write docs\",\"completed\":false}"
```

Update:

```bash
curl -X PUT http://localhost:8080/todos/1 -H "Content-Type: application/json" -d "{\"title\":\"Write better docs\",\"completed\":true}"
```

## Validation and error behavior

- `title` is required for create requests.
- Blank title is rejected on update.
- Unknown IDs return `404 Not Found`.

## Testing

```bash
mvn test
```

## Legacy notes

Short link-style notes are still available in `tutorials.md`.
