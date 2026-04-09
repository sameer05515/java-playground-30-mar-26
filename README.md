# java-playground-30-mar-26

Hands-on playground for Java and Node.js experiments with Kafka, Redis, and Swagger/OpenAPI.

## Repository purpose

This repository contains small, focused projects for:

- Kafka producer/consumer patterns (Spring Boot and Node.js)
- Redis caching in plain Java and Spring Boot
- Swagger/OpenAPI documentation with a simple TODO API
- A combined Redis + Kafka + MongoDB TODO service

The projects are intentionally lightweight and are meant for learning and experimentation.

## Project map

### `kafka/`

- `kafka-demo`: Spring Boot Kafka producer/consumer with a REST endpoint
- `kafka-consumer-node`: standalone Node.js Kafka consumers (`consumer.v1`, `consumer.v2`)
- `todo-app`: Node.js TODO API with Redis cache + Kafka-driven cache invalidation
- `docker`: local Kafka broker setup

### `redis/`

- `redis-demo`: plain Java + Jedis
- `redis-demo-spring-boot`: Spring Boot API with Redis-backed caching
- `redis-kafka-demo-sb`: Spring Boot TODO CRUD with MongoDB persistence, Redis cache, and Kafka events
- `redis-docker`: local Redis setup

### `swagger/`

- `swagger-demo`: Spring Boot TODO CRUD API documented using springdoc OpenAPI

## Prerequisites

- Java 17+
- Maven 3.9+
- Node.js 18+ and npm
- Docker Desktop (or Docker Engine)

## Infrastructure quick start

From repository root:

```bash
docker compose -f kafka/docker/docker-compose.yml up -d
docker compose -f redis/redis-docker/docker-compose.yml up -d
```

For `redis/redis-kafka-demo-sb`, MongoDB is also required:

```bash
docker run -d --name mongo -p 27017:27017 mongo:7
```

## Running modules

### Spring Boot modules

Use Maven wrapper from each project folder (or `mvn spring-boot:run` if wrapper is unavailable):

```bash
./mvnw spring-boot:run
```

### Node.js modules

Install dependencies once per Node project:

```bash
npm install
```

Run scripts:

- `kafka/kafka-consumer-node`: `npm run start:v1` or `npm run start:v2`
- `kafka/todo-app`: `npm run start` (API) and `npm run start:consumer` (consumer)

## Ports and default URLs

- Kafka broker: `localhost:9092`
- Redis: `localhost:6379`
- MongoDB: `localhost:27017`
- Swagger demo API/UI: `http://localhost:8080` / `http://localhost:8080/swagger-ui/index.html`
- Redis Spring demo: `http://localhost:8085`
- Redis+Kafka+Mongo demo: `http://localhost:8089`

## API highlights

- Kafka Spring demo: `GET /kafka/send?msg=hello`
- Redis Spring demo: `GET /welcome` (cached response)
- Swagger demo: `GET/POST/PUT/DELETE /todos`
- Redis+Kafka+Mongo demo: `GET/POST/PUT/DELETE /todos` with event publishing and cache invalidation

## Testing

Run per module:

```bash
mvn test
```

Node examples currently do not include automated tests.

## Existing tutorial notes

The original quick notes are kept in:

- `kafka/tutorials.md`
- `redis/tutorials.md`
- `swagger/tutorials.md`