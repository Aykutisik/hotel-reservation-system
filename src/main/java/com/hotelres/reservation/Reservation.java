package com.hotelres.reservation;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

import java.time.LocalDate;


@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    private Long reservationId;
    private Long hotelId;
    private Long roomTypeId;
    private LocalDate startDate;
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Long guestId;
    private int roomCount;

    protected Reservation() {
    }

}
