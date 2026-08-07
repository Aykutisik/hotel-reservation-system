package com.hotelres.reservation;

/** Rezervasyon baska bir misafire ait -> HTTP 403. */
public class ReservationAccessDeniedException extends RuntimeException {

    public ReservationAccessDeniedException(String reservationId) {
        super("Reservation does not belong to this guest: " + reservationId);
    }
}
