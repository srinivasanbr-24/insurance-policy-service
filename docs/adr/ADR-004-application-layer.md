# ADR-004 Application Layer and Use Case Orchestration

## Status

Accepted

## Context

The system requires clear separation between business workflows and infrastructure concerns.

Business use cases must remain independent of:

- Spring Framework
- JPA/Hibernate
- Kafka
- Redis
- REST Controllers

## Decision

Introduce an Application Layer consisting of:

- Input Ports (Use Cases)
- Output Ports (Infrastructure Contracts)
- Application Services

Application Services coordinate business workflows while domain aggregates enforce business rules and invariants.

## Alternatives Considered

### Direct Service-to-Repository Design

Rejected because:

- Couples business workflows to persistence concerns.
- Makes testing difficult.
- Violates Clean Architecture dependency rules.

### Anemic Service Layer

Rejected because:

- Leads to duplicated orchestration logic.
- Reduces maintainability.

## Consequences

Positive:

- Framework-independent business workflows.
- Easier unit testing.
- Clear separation of responsibilities.
- Improved maintainability.

Negative:

- Additional abstraction layers.
- More interfaces to manage.