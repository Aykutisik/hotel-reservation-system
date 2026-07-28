# Hotel Reservation System

A hotel reservation system built with Spring Boot. The goal is a layered
architecture grounded in object-oriented design principles (encapsulation,
polymorphism, SOLID).

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 26 |
| Framework | Spring Boot 4.1.0 |
| Data access | Spring Data JPA / Hibernate |
| Database | H2 (development), PostgreSQL (planned) |
| Build | Maven (wrapper included) |

## Running

No Maven installation required, the wrapper ships with the project:

```bash
./mvnw spring-boot:run
```

- Application: http://localhost:8080
- H2 console: http://localhost:8080/h2-console

## Planned Package Structure

```
com.hotelres
├── domain/
│   ├── model/       Room, Guest, Reservation, BaseEntity
│   ├── enums/       RoomType, ReservationStatus
│   └── pricing/     PricingRule and its strategies
├── repository/      Spring Data JPA interfaces
├── service/         business logic
├── controller/      REST endpoints
├── dto/             request/response models
├── mapper/          entity <-> dto mapping
└── exception/       custom exceptions and global handler
```

## Roadmap

- [x] Project skeleton
- [ ] Domain model (Room, Guest, Reservation)
- [ ] Reservation service and date overlap checks
- [ ] Pricing strategies (seasonal, weekend, long stay)
- [ ] REST API and DTO layer
- [ ] Global exception handling
- [ ] Unit tests
- [ ] Migration to PostgreSQL
