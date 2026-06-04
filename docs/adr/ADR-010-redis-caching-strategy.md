# ADR-009: Introduce Cache Abstraction for Policy Retrieval

## Status

Accepted

## Date

2026-06-04

---

## Context

Policy retrieval is expected to be the most frequently executed operation in the Insurance Policy Management Service.

Repeated reads of the same policy would unnecessarily:

* Increase database load
* Increase response latency
* Reduce scalability

The application should remain independent of any specific caching technology and continue to follow Clean Architecture principles.

---

## Decision

Introduce a cache abstraction through an outbound port:

```java
public interface PolicyCache {

    Optional<Policy> findById(PolicyId id);

    void put(Policy policy);

    void evict(PolicyId id);
}
```

Application services interact only with the port.

A Redis-based implementation is provided in the infrastructure layer.

### Read Path

```text
GetPolicyService
        |
        v
   PolicyCache
        |
        +--> Cache Hit
        |
        +--> Cache Miss
                  |
                  v
           PolicyRepository
                  |
                  v
            Cache Update
```

### Write Path

```text
FlagPoliciesService
       |
       +--> Save Policy
       |
       +--> Evict Cache Entry
       |
       +--> Publish Event
```

---

## Consequences

### Benefits

* Reduced database traffic
* Faster policy retrieval
* Better scalability
* Technology-independent application layer
* Redis can be replaced without impacting business logic

### Trade-offs

* Additional infrastructure dependency
* Cache consistency must be maintained
* Cache invalidation logic required for write operations

---

## Architecture Impact

### Domain Layer

No changes.

### Application Layer

Introduced:

* PolicyCache port

Updated:

* GetPolicyService
* FlagPoliciesService

### Infrastructure Layer

Added:

* RedisPolicyCache
* CacheConfig

Configured:

* CacheManager
* RedisTemplate

### Testing

Added cache-focused tests covering:

* Cache miss scenarios
* Cache hit scenarios
* Cache population
* Cache eviction after updates

---

## Decision Outcome

Caching is implemented as an infrastructure concern hidden behind an outbound port, preserving Clean Architecture boundaries while improving performance and scalability.
