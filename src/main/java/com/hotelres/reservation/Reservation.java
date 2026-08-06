package com.hotelres.reservation;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.time.LocalDate;


@Entity
@Table(name = "reservation")
public class Reservation {

    /**
     * Idempotency key -- istemci uretir, sunucu asla uretmez.
     */
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

    public void cancel() {
        if (status == Status.CANCELED) {
            throw new IllegalArgumentException("Reservation already cancelled");
        }
        status = Status.CANCELED;
    }

    public String getReservationId() {
        return reservationId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Status getStatus() {
        return status;
    }

    public Long getGuestId() {
        return guestId;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
