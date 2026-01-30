Operations Request Tracker – High Level Design (HLD)
1. Introduction
The Operations Request Tracker is a web-based application designed to manage operational requests through a defined lifecycle. It provides visibility, accountability, and tracking of requests that are otherwise managed manually via emails or spreadsheets.
2. Business Problem
Organizations often face challenges in tracking operational requests due to lack of centralized systems. This leads to delays, missing ownership, and poor auditability.
3. Actors
- Requester: Creates a request
- Admin: Processes and updates request status
- System: Manages persistence and workflow
4. System Scope
In Scope:
- Create and track requests
- Update request status

Out of Scope:
- Authentication
- Notifications
- SLA management
5. High-Level Architecture
Client (React UI) communicates with Spring Boot backend via REST APIs. The backend persists data in a relational database.
6. Core Entity – Request
Fields:
- id
- requestType
- description
- status
- createdAt
- updatedAt
7. Request Lifecycle
NEW → IN_PROGRESS → COMPLETED / REJECTED
8. API Overview
POST /requests – Create request
GET /requests – View all requests
GET /requests/{id} – View request
PUT /requests/{id}/status – Update status
9. Non-Functional Considerations
- Scalability: Can evolve to event-driven architecture
- Maintainability: Layered architecture
- Reliability: Transactional consistency
10. Future Enhancements
- Kafka integration
- Authentication & authorization
- Notifications
- Domain specialization (e.g. Real Estate Maintenance)
