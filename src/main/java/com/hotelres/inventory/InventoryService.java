package com.hotelres.inventory;


import com.hotelres.reservation.Reservation;
import com.hotelres.reservation.ReservationRepository;
import com.hotelres.reservation.Status;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    private final ReservationRepository reservationRepository;

    public InventoryService(InventoryRepository inventoryRepository, ReservationRepository reservationRepository) {
        this.inventoryRepository = inventoryRepository;
        this.reservationRepository = reservationRepository;
    }


    @Transactional
    public void reserve(LocalDate checkInDate, LocalDate checkOutDate, Long hotelId, Long roomTypeId, Integer reservationQuantity, Long guestId) {
        List<RoomTypeInventory> nights = inventoryRepository.findNights(hotelId, roomTypeId, checkInDate, checkOutDate.minusDays(1));
        for (RoomTypeInventory night : nights) {
            night.reserve(reservationQuantity);
            inventoryRepository.save(night);
        }
        Reservation reservation = Reservation.of(UUID.randomUUID().toString(), hotelId, roomTypeId, checkInDate, checkOutDate, guestId, reservationQuantity);
        reservationRepository.save(reservation);
    }

    public void release(String reservationId, Long guestId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation != null) {
            reservationRepository.delete(reservation);
            List<RoomTypeInventory> nights = inventoryRepository.findNights(reservation.getHotelId(),
                    reservation.getRoomTypeId(), reservation.getStartDate(), reservation.getEndDate());

        }

    }


}
