# ADR-003 Rich Domain Model

## Status

Accepted

## Context

Insurance policies contain complex business rules.

These rules should remain inside the domain model rather than being distributed across services.

## Decision

Use:

- Aggregate Root
- Value Objects
- Domain Events

The Policy aggregate will enforce business invariants.

## Alternatives Considered

### Anemic Domain Model

Rejected because:

- Business logic becomes fragmented.
- Validation duplicated across services.

## Consequences

- Better encapsulation
- Higher testability
- Stronger business consistency