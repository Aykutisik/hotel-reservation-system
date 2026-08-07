package com.hotelres.reservation;


import com.hotelres.inventory.InventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * Rezervasyon is akisini yonetir: envanteri dusurmek ile rezervasyon kaydini
 * yazmak ayni transaction icinde olur -- ya ikisi birden ya hicbiri.
 */
@Service
public class ReservationService {

    private final InventoryService inventoryService;

    private final ReservationRepository reservationRepository;

    public ReservationService(InventoryService inventoryService,
                              ReservationRepository reservationRepository) {
        this.inventoryService = inventoryService;
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    public Reservation reserve(ReservationCommand cmd) {
        inventoryService.reserve(cmd.hotelId(), cmd.roomTypeId(),
                cmd.startDate(), cmd.endDate(), cmd.roomCount());

        return reservationRepository.save(Reservation.of(cmd));
    }

    @Transactional
    public void cancel(String reservationId, Long guestId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException(reservationId));

        if (!Objects.equals(reservation.getGuestId(), guestId)) {
            throw new ReservationAccessDeniedException(reservationId);
        }

        reservation.cancel();

        inventoryService.release(reservation.getHotelId(), reservation.getRoomTypeId(),
                reservation.getStartDate(), reservation.getEndDate(), reservation.getRoomCount());
    }
}
