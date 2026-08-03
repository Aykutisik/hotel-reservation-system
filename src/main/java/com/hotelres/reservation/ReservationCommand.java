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
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("End date should be before start date");
        }
        if (roomCount < 1) {
            throw new IllegalArgumentException("Room count should be greater than 0");
        }
    }

    public int nights() {
        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }
}
