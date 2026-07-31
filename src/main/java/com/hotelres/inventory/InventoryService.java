package com.hotelres.inventory;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }


    @Transactional
    public void reserve(LocalDate checkInDate, LocalDate checkOutDate, Long hotelId, Long roomTypeInventory) {
        inventoryRepository.isRoomAvailable(checkInDate, checkOutDate, hotelId, roomTypeInventory);
    }

}
