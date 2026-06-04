# ADR-009: Kafka Event Publishing for Policy Flagging

## Status

Accepted

## Context

The Insurance Policy Management service supports flagging policies for manual review.

When a policy is flagged, downstream systems such as:

* Fraud Detection
* Risk Assessment
* Audit Services
* Notification Services

may need to react asynchronously.

Direct service-to-service communication would tightly couple the Policy service to consumers and reduce scalability.

A messaging solution is required to publish domain events after successful policy state changes.

---

## Decision

Apache Kafka will be used as the event backbone.

A dedicated outbound port will be introduced:

```java
PolicyEventPublisher
```

The application service publishes a domain event after a policy is successfully flagged.

```java
PolicyFlaggedEvent
```

Infrastructure implements the port using Spring Kafka.

```java
KafkaPolicyEventPublisher
```

Events are serialized into:

```java
PolicyFlaggedMessage
```


---

## Consequences

### Positive

* Loose coupling between services
* Event-driven architecture support
* Horizontal scalability
* Clear separation between domain and infrastructure
* Infrastructure can be replaced without changing business logic

### Negative

* Additional operational complexity
* Event delivery monitoring required
* Event schema versioning must be maintained

---

## Alternatives Considered

### REST Call

Rejected because of tight coupling and synchronous dependencies.

### Database Polling

Rejected due to latency and inefficiency.

### Spring Application Events

Rejected because events would remain local to the service boundary.

---

## Result

The Policy service publishes Kafka events whenever policies are flagged for review while preserving Clean Architecture boundaries.
