# ADR-008: Persistence Implementation Strategy

## Status

Accepted

## Context

The Insurance Policy Management Microservice requires a persistence mechanism that:

* Preserves domain model purity
* Supports transactional consistency
* Enables optimistic locking
* Supports auditing requirements
* Allows dynamic policy search
* Maintains separation between domain and infrastructure layers

The application follows Clean Architecture and Hexagonal Architecture principles, requiring the domain model to remain independent of JPA and database concerns.

## Decision

The persistence layer will be implemented using:

* Spring Data JPA
* PostgreSQL
* Flyway database migrations
* Dedicated JPA entities
* Repository Adapter pattern
* Persistence-to-domain mapping layer
* JPA Specifications for dynamic filtering
* Spring Data Auditing
* Optimistic locking using @Version

The persistence architecture is:

Domain Repository Port
→ Repository Adapter
→ Spring Data Repository
→ JPA Entity
→ PostgreSQL

Domain models remain free of JPA annotations.

Dedicated persistence entities are responsible for database mapping.

## Implementation

The following components are introduced:

### Persistence Entities

* PolicyJpaEntity
* BaseAuditEntity

### Repository Layer

* SpringDataPolicyRepository
* PolicySpecification

### Adapter Layer

* PolicyRepositoryAdapter

### Mapping Layer

* PolicyPersistenceMapper

### Infrastructure Configuration

* JpaConfig

### Database Migration

* Flyway migration scripts

## Consequences

### Positive

* Strong separation between domain and persistence concerns
* Easier unit testing of domain logic
* Support for complex search requirements
* Schema evolution is version-controlled
* Consistent auditing support
* Optimistic locking prevents lost updates

### Negative

* Additional mapping code
* More infrastructure classes
* Increased implementation complexity compared to direct entity exposure

## Alternatives Considered

### Domain Objects as JPA Entities

Rejected because it introduces persistence concerns into the domain layer.

### JDBC Template

Rejected because it increases boilerplate and reduces maintainability.

### Hibernate Auto-DDL Only

Rejected because schema changes would not be explicitly versioned and controlled.

## Related ADRs

* ADR-002 Database Strategy
* ADR-003 Rich Domain Model
* ADR-005 Policy Search Strategy
* ADR-006 Persistence Adapter and Aggregate Rehydration
