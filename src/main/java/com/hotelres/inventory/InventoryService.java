package com.hotelres.inventory;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }


    @Transactional
    public void reserve(RoomTypeInventory roomTypeInventory) {
        roomTypeInventory
        inventoryRepository.save(roomTypeInventory);
    }

}
