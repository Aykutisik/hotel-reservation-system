package com.hotelres.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface InventoryRepository extends JpaRepository<RoomTypeInventory, RoomTypeInventoryId> {

    /**
     * Verilen tarih araligindaki envanter satirlarini getirir.
     * "between" iki ucu da dahil ettigi icin cagiran taraf son geceyi (checkOut - 1) gecmelidir.
     */
    @Query("""
            select i from RoomTypeInventory i
             where i.id.hotelId = :hotelId
               and i.id.roomTypeId = :roomTypeId
               and i.id.inventoryDate between :from and :to
            """)
    List<RoomTypeInventory> findNights(@Param("hotelId") Long hotelId,
                                       @Param("roomTypeId") Long roomTypeId,
                                       @Param("from") LocalDate from,
                                       @Param("to") LocalDate to);
}
