package com.hotelres.reservation;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.Getter;

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

    protected Reservation() {
    }

    public static Reservation of(String id, Long hotelId, Long roomTypeId, LocalDate startDate, LocalDate endDate, Long guestId, int roomCount) {
        Reservation reservation = new Reservation();
        reservation.reservationId = id;
        reservation.hotelId = hotelId;
        reservation.roomTypeId = roomTypeId;
        reservation.startDate = startDate;
        reservation.endDate = endDate;
        reservation.status = Status.PENDING;
        reservation.guestId = guestId;
        reservation.roomCount = roomCount;
        return reservation;
    }

}
