# ADR-002 Database Strategy

## Status

Accepted

## Context

The service must support PostgreSQL and SQL Server.

Schema evolution must be versioned and automated.

## Decision

Use:

- Flyway
- UUID primary keys
- Spring Data JPA
- Optimistic locking
- Database-agnostic SQL

## Consequences

- Predictable deployments
- Easier rollback strategy
- Environment consistency