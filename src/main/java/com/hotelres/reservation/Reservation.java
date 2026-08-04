package com.hotelres.reservation;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;


@Entity
@Getter
@Table(name = "reservation")
public class Reservation {

    @Id
    private String reservationId;

    private Long hotelId;
    private Long roomTypeId;
    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Long guestId;
    private int roomCount;

    private Instant createdAt;

    protected Reservation() {
    }

    public static Reservation of(ReservationCommand cmd) {
        Reservation reservation = new Reservation();
        reservation.reservationId = cmd.reservationId();
        reservation.hotelId = cmd.hotelId();
        reservation.roomTypeId = cmd.roomTypeId();
        reservation.startDate = cmd.startDate();
        reservation.endDate = cmd.endDate();
        reservation.guestId = cmd.guestId();
        reservation.roomCount = cmd.roomCount();
        reservation.status = Status.PENDING;
        reservation.createdAt = Instant.now();
        return reservation;
    }
}
