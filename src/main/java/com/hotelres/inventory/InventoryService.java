package com.hotelres.inventory;


import com.hotelres.reservation.Reservation;
import com.hotelres.reservation.ReservationCommand;
import com.hotelres.reservation.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    private final ReservationRepository reservationRepository;

    public InventoryService(InventoryRepository inventoryRepository, ReservationRepository reservationRepository) {
        this.inventoryRepository = inventoryRepository;
        this.reservationRepository = reservationRepository;
    }


    @Transactional
    public Reservation reserve(ReservationCommand cmd) {

        List<RoomTypeInventory> nights = inventoryRepository.findNights(
                cmd.hotelId(), cmd.roomTypeId(), cmd.startDate(), cmd.endDate().minusDays(1));
        if (cmd.nights() != nights.size()) {
            throw new NoInventoryException("Envanter satiri eksik: " + nights.size() + "/" + cmd.nights());
        }
        for (RoomTypeInventory night : nights) {
            night.reserve(cmd.roomCount());
            inventoryRepository.save(night);
        }
        return reservationRepository.save(Reservation.of(cmd));
    }


    @Transactional
    public void release(String reservationId, Long guestId) {

        Reservation reservation = reservationRepository.findById(reservationId).
                orElseThrow(() -> new IllegalStateException("Reservation id " + reservationId + " not found"));

        if(!Objects.equals(reservation.getGuestId(), guestId)) {
            throw new IllegalStateException("The reservation is not belonged by Guest id " + guestId);
        }
        reservation.cancel();
        reservationRepository.save(reservation);
        List<RoomTypeInventory> nights = inventoryRepository.findNights(reservation.getHotelId(),
                reservation.getRoomTypeId(), reservation.getStartDate(), reservation.getEndDate().minusDays(1));
        for (RoomTypeInventory night : nights) {
            night.release(reservation.getRoomCount());
            inventoryRepository.save(night);
        }

    }


}
