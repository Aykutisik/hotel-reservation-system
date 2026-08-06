package com.hotelres.reservation;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record ReservationCommand(
        String reservationId,
        Long hotelId,
        Long roomTypeId,
        LocalDate startDate,
        LocalDate endDate,
        Long guestId,
        int roomCount
) {

    public ReservationCommand {
        if (!startDate.isBefore(endDate)) {
            throw new IllegalArgumentException("startDate must be before endDate");
        }
        if (roomCount < 1) {
            throw new IllegalArgumentException("roomCount must be greater than zero");
        }
    }


    public int nights() {
        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }
}
