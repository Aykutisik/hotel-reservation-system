# Hotel Reservation System

Spring Boot ile geliştirilen otel rezervasyon sistemi. Nesne yönelimli tasarım
prensipleri (encapsulation, polimorfizm, SOLID) üzerine kurulu katmanlı bir mimari
hedefleniyor.

## Teknolojiler

| Katman | Teknoloji |
|---|---|
| Dil | Java 26 |
| Framework | Spring Boot 4.1.0 |
| Veri erişimi | Spring Data JPA / Hibernate |
| Veritabanı | H2 (geliştirme), PostgreSQL (planlanan) |
| Build | Maven (wrapper dahil) |

## Çalıştırma

Maven kurulumuna gerek yok, wrapper projeyle birlikte geliyor:

```bash
./mvnw spring-boot:run
```

- Uygulama: http://localhost:8080
- H2 konsolu: http://localhost:8080/h2-console

## Planlanan Paket Yapısı

```
com.hotelres
├── domain/
│   ├── model/       Room, Guest, Reservation, BaseEntity
│   ├── enums/       RoomType, ReservationStatus
│   └── pricing/     PricingRule ve stratejileri
├── repository/      Spring Data JPA arayüzleri
├── service/         iş mantığı
├── controller/      REST uçları
├── dto/             istek/yanıt modelleri
├── mapper/          entity <-> dto dönüşümü
└── exception/       özel hatalar ve global handler
```

## Yol Haritası

- [x] Proje iskeleti
- [ ] Domain modeli (Room, Guest, Reservation)
- [ ] Rezervasyon servisi ve tarih çakışma kontrolü
- [ ] Fiyatlandırma stratejileri (sezon, hafta sonu, uzun konaklama)
- [ ] REST API ve DTO katmanı
- [ ] Global exception handling
- [ ] Birim testleri
- [ ] PostgreSQL'e geçiş
