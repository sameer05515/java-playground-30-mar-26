# Redis Playground

This folder contains Redis examples ranging from plain Java to Spring Boot with Kafka and MongoDB integration.

## Contents

- `redis-demo`: plain Java example using Jedis
- `redis-demo-spring-boot`: Spring Boot API with Redis-backed response caching
- `redis-kafka-demo-sb`: Spring Boot TODO CRUD with MongoDB persistence, Redis cache, and Kafka event-driven invalidation
- `redis-docker`: local Redis setup

## Prerequisites

- Docker
- Java 17+
- Maven 3.9+
- Kafka broker for `redis-kafka-demo-sb` (`localhost:9092`)
- MongoDB for `redis-kafka-demo-sb` (`localhost:27017`)

## Start Redis

```bash
docker compose -f redis-docker/docker-compose.yml up -d
```

## Module: `redis-demo` (plain Java + Jedis)

### What it demonstrates

- Basic Redis client connection and operations without Spring

### Run

```bash
cd redis-demo
mvn clean package
java -jar target/redis-demo-1.0-SNAPSHOT.jar
```

## Module: `redis-demo-spring-boot`

### What it demonstrates

- Spring Data Redis integration
- `RedisTemplate<String, String>` custom configuration
- Cache-aside style behavior in `/welcome`

### Runtime behavior

- Endpoint: `GET /welcome`
- Cache key: `cached_welcome_msg`
- TTL: `60` seconds
- Default port: `8085`

### Run

```bash
cd redis-demo-spring-boot
./mvnw spring-boot:run
```

## Module: `redis-kafka-demo-sb`

### What it demonstrates

- TODO CRUD persisted in MongoDB
- Cached TODO list in Redis (`cached_todos_list`, TTL `180` seconds)
- Kafka event publishing on create/update/delete
- Kafka consumer clears Redis cache on TODO change events

### Configuration summary

From `application.yml`:

- Redis: `localhost:6379`
- MongoDB: `mongodb://localhost:27017/redis_kafka_demo`
- Kafka: `localhost:9092`
- Topic: `todo-events`
- App port: `8089`

### Run

```bash
cd redis-kafka-demo-sb
./mvnw spring-boot:run
```

### API examples

```bash
curl http://localhost:8089/todos
curl -X POST http://localhost:8089/todos -H "Content-Type: application/json" -d "{\"title\":\"Learn Kafka\",\"completed\":false}"
curl -X PUT http://localhost:8089/todos/{id} -H "Content-Type: application/json" -d "{\"title\":\"Learn Kafka deeply\",\"completed\":true}"
curl -X DELETE http://localhost:8089/todos/{id}
```

## Testing

```bash
mvn test
```

`redis-kafka-demo-sb` includes unit tests for controller/service/consumer logic and JaCoCo reporting.

## Legacy notes

Short external learning references are available in `tutorials.md`.
