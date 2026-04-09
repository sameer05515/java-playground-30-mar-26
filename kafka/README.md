# Kafka Playground

This folder contains Kafka-focused examples in both Spring Boot and Node.js.

## Contents

- `kafka-demo`: Spring Boot app with producer, consumer, and topic auto-creation
- `kafka-consumer-node`: standalone Kafka consumers in Node.js
- `todo-app`: Node.js TODO API that publishes Kafka events and uses Redis caching
- `docker`: local Kafka single-broker setup

## Prerequisites

- Docker
- Java 17+, Maven (for `kafka-demo`)
- Node.js 18+ (for Node projects)

## Start Kafka broker

```bash
docker compose -f docker/docker-compose.yml up -d
```

Verify broker container:

```bash
docker ps
```

## Module: `kafka-demo` (Spring Boot)

### What it demonstrates

- Topic creation via `KafkaTopicConfig` (`test` topic)
- REST endpoint to publish messages
- Consumer group consuming from earliest offset

### Configuration

Defined in `kafka-demo/src/main/resources/application.yml`:

- `spring.kafka.bootstrap-servers=localhost:9092`
- consumer group: `test-group`
- topic name: `test` (from config class)

### Run

```bash
cd kafka-demo
./mvnw spring-boot:run
```

### Send message

```bash
curl "http://localhost:8080/kafka/send?msg=hello-kafka"
```

## Module: `kafka-consumer-node`

### What it demonstrates

- Basic KafkaJS consumer setup
- Two consumer variants (`consumer.v1.js` and `consumer.v2.js`)

### Run

```bash
cd kafka-consumer-node
npm install
npm run start:v2
```

To run v1:

```bash
npm run start:v1
```

## Module: `todo-app` (Node + Kafka + Redis)

### What it demonstrates

- Express TODO API (`GET/POST/PUT /todos`)
- Redis caching for list endpoint (`todos:all`)
- Kafka event production on create/update
- Separate consumer process clears cache on events

### Run

Start API:

```bash
cd todo-app
npm install
npm run start
```

Start consumer (separate terminal):

```bash
cd todo-app
npm run start:consumer
```

### API examples

```bash
curl http://localhost:3000/todos
curl -X POST http://localhost:3000/todos -H "Content-Type: application/json" -d "{\"title\":\"Buy milk\"}"
curl -X PUT http://localhost:3000/todos/1 -H "Content-Type: application/json" -d "{\"title\":\"Buy coffee\"}"
```

## Troubleshooting

- If consumers do not receive events, confirm Kafka is up and reachable at `localhost:9092`.
- If TODO list stays stale, ensure the `todo-app` consumer process is running.
- If ports conflict, stop old containers/processes and restart.

## Legacy notes

Short tutorial references are still available in `tutorials.md`.
