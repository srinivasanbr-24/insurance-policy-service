# ADR-005 Policy Search Strategy

## Status

Accepted

## Context

The Policy Search API supports:

- Pagination
- Sorting
- Status filtering
- Line Of Business filtering
- Region filtering
- Effective date ranges
- Free text search

The repository contract must support these requirements without exposing persistence implementation details.

## Decision

Expose a search abstraction through the PolicyRepository port.

Repository operations will return a PolicySearchResult containing:

- Content
- Pagination metadata
- Total record count

Summary aggregations will be exposed through dedicated projection models.

Persistence implementations will use JPA Specifications during infrastructure implementation.

## Alternatives Considered

### findAll() Repository Pattern

Rejected because:

- Loads excessive data.
- Does not support pagination.
- Does not scale for enterprise datasets.

### CQRS Split Repositories

Rejected because:

- Adds unnecessary complexity for current scope.
- Single repository contract sufficiently supports requirements.

## Consequences

Positive:

- Flexible querying capability.
- Clean separation of application and persistence concerns.
- Efficient SQL generation in future persistence adapters.

Negative:

- Slightly more complex repository contract.