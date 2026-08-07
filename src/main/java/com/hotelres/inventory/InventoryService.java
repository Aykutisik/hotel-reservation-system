package com.hotelres.inventory;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Sadece envanter sayaclarini yonetir. Rezervasyon kavramindan habersizdir --
 * kim, neden rezerve etti bilmez; yalnizca "su gecelerde su kadar oda" der.
 */
@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public void reserve(Long hotelId, Long roomTypeId, LocalDate checkIn, LocalDate checkOut, int rooms) {
        for (RoomTypeInventory night : nights(hotelId, roomTypeId, checkIn, checkOut)) {
            night.reserve(rooms);
        }
    }

    @Transactional
    public void release(Long hotelId, Long roomTypeId, LocalDate checkIn, LocalDate checkOut, int rooms) {
        for (RoomTypeInventory night : nights(hotelId, roomTypeId, checkIn, checkOut)) {
            night.release(rooms);
        }
    }

    /**
     * Aralikatki her gece icin envanter satirini getirir. Aralik [checkIn, checkOut):
     * cikis gunu gece sayilmaz, o yuzden sorguya checkOut - 1 gecilir.
     */
    private List<RoomTypeInventory> nights(Long hotelId, Long roomTypeId, LocalDate checkIn, LocalDate checkOut) {
        List<RoomTypeInventory> nights = inventoryRepository.findNights(
                hotelId, roomTypeId, checkIn, checkOut.minusDays(1));

        long expected = ChronoUnit.DAYS.between(checkIn, checkOut);
        if (nights.size() != expected) {
            throw new NoInventoryException("Envanter satiri eksik: " + nights.size() + "/" + expected);
        }
        return nights;
    }
}
