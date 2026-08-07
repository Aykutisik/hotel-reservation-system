package com.hotelres.reservation;

/** Verilen id ile rezervasyon bulunamadi -> HTTP 404. */
public class ReservationNotFoundException extends RuntimeException {

    public ReservationNotFoundException(String reservationId) {
        super("Reservation not found: " + reservationId);
    }
}
