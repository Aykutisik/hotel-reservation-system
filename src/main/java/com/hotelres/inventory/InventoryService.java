package com.hotelres.inventory;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }


    @Transactional
    public void reserve(LocalDate checkInDate, LocalDate checkOutDate, Long hotelId, Long roomTypeId, Integer reservationQuantity) {
        List<RoomTypeInventory> nights = inventoryRepository.findNights(hotelId, roomTypeId, checkInDate, checkOutDate);
        for (RoomTypeInventory night : nights) {
            night.reserve(reservationQuantity);
            inventoryRepository.save(night);
        }

    }

}
