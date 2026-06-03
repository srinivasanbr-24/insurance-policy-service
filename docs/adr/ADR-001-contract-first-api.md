# **ADR-001**

## **Contract First API Design**

#### Context

Insurance APIs are consumed by multiple downstream systems.

API changes must be governed.

#### Decision

Use OpenAPI 3.0 as the single source of truth.

Generate interfaces using OpenAPI Generator.

#### Alternatives
* Spring MVC code first 
* Swagger annotation first

#### Consequences
* Better API governance 
* Reduced contract drift 
* Independent frontend/backend development