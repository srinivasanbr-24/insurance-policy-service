# Insurance Policy Management Microservice

## Overview

Production-grade Insurance Policy Management Service built using:

* Java 21
* Spring Boot 3.x
* OpenAPI Contract First
* Clean Architecture
* Hexagonal Architecture
* Kafka
* Redis
* PostgreSQL / SQL Server
* Testcontainers

---

## Architecture

```text
Client
  |
REST API
  |
Inbound Adapter
  |
Application Layer
  |
Domain Layer
  |
Outbound Ports
  |
Infrastructure Adapters
```

---

## API Contract

The API contract is defined in:

```text
api-contract/policy-api.yaml
```

Controller interfaces are generated from the contract.

No controller methods should be manually defined.

---

## Build

```bash
mvn clean verify
```

---

## Generate OpenAPI Sources

```bash
mvn clean generate-sources
```

---

## Run Application

```bash
mvn spring-boot:run
```

---

## API Documentation

Swagger UI:

```text
http://localhost:8080/swagger-ui.html
```

---

## Development Process

This project follows:

* Contract First Development
* Test Driven Development
* Conventional Commits
* Clean Architecture
* ADR Documentation

---

## Commit Strategy

Examples:

```bash
feat(openapi): establish insurance policy management API contract

feat(database): introduce policy schema and persistence foundation

feat(domain): implement policy aggregate and business rules
```

---

## Architecture Decisions

See:

```text
docs/adr
```

---

## Development Journal

See:

```text
docs/development-journal.md
```
