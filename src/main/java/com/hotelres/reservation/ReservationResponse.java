package com.hotelres.reservation;

import java.time.Instant;
import java.time.LocalDate;

/**
 * Rezervasyonun API'ye acilan yuzu. Entity'yi dogrudan donmuyoruz ki
 * veritabani semasi ile API sozlesmesi birbirine bagli olmasin.
 */
public record ReservationResponse(
        String reservationId,
        Long hotelId,
        Long roomTypeId,
        LocalDate startDate,
        LocalDate endDate,
        int roomCount,
        Status status,
        Instant createdAt
) {

    public static ReservationResponse from(Reservation reservation) {
        return new ReservationResponse(
                reservation.getReservationId(),
                reservation.getHotelId(),
                reservation.getRoomTypeId(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getRoomCount(),
                reservation.getStatus(),
                reservation.getCreatedAt());
    }
}
