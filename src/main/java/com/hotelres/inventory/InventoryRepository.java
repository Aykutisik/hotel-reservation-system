package com.hotelres.inventory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface InventoryRepository extends JpaRepository<RoomTypeInventory, RoomTypeInventoryId> {


    public List<RoomTypeInventory> findBy(LocalDate checkInDate, LocalDate checkOutDate, Long hotelId, Long roomTypeInventory);
}
