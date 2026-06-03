# ADR-006 Persistence Adapter and Aggregate Rehydration Strategy

## Status

Accepted

## Context

The application layer requires persistence capabilities while maintaining strict adherence to Clean Architecture principles.

The domain model must remain independent of:

- Spring Framework
- JPA/Hibernate
- Database-specific concerns

Additionally, persistence adapters require a safe mechanism to reconstruct domain aggregates from stored data without introducing mutable setters or leaking persistence concerns into the domain layer.

The Policy Search API also requires:

- Pagination
- Sorting
- Status filtering
- Line Of Business filtering
- Region filtering
- Effective date range filtering
- Free-text searching

## Decision

### Persistence Strategy

Implement persistence using:

- Spring Data JPA
- JpaSpecificationExecutor
- Repository Adapter Pattern
- Entity-to-Domain Mapping Layer

The application repository port will be implemented by an infrastructure adapter.

### Aggregate Rehydration Strategy

The Policy aggregate will expose:

- create(...)
- restore(...)

factory methods.

Constructors will remain private.

Persistence adapters will use restore(...) when rebuilding aggregates from database entities.

Business workflows will use create(...) when creating new aggregates.

### Search Strategy

Policy searching will be implemented using JPA Specifications to support dynamic filtering, sorting, and pagination requirements.

### Mapping Strategy

Separate persistence entities from domain aggregates.

Example:

PolicyJpaEntity
↔
PolicyPersistenceMapper
↔
Policy

The domain model remains persistence-agnostic.

## Alternatives Considered

### Annotating Domain Objects with JPA

Example:

```java
@Entity
public class Policy