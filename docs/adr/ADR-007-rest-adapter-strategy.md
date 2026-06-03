# ADR-006 REST API Adapter Layer

## Status
Accepted

## Decision
Implement REST endpoints through OpenAPI-generated interfaces and keep generated models isolated from the domain model using PolicyMapper.

## Rationale
- Preserves Clean Architecture boundaries.
- Prevents OpenAPI models from leaking into domain layer.
- Enables independent API contract evolution.

## Consequences
### Positive
- Strong separation of concerns.
- Easier testing.
- Contract-first compliance.

### Negative
- Additional mapping code required.