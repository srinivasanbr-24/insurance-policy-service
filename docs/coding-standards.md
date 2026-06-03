# Coding Standards

## Architecture

- Domain layer must not depend on Spring.
- Domain layer must not depend on JPA.
- Infrastructure depends on Application.
- Application depends on Domain.

## Testing

- TDD required.
- Unit tests for every Aggregate.
- Unit tests for every Value Object.

## Commits

Use Conventional Commits.

Example:

feat(domain): implement policy aggregate and core business rules