package com.hotelres.inventory;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "room_type_inventory")
public class RoomTypeInventory {

    private static final int OVERBOOKING_NUMERATOR = 11;
    private static final int OVERBOOKING_DENOMINATOR = 10;

    @EmbeddedId
    private RoomTypeInventoryId id;

    private int totalInventory;

    private int totalReserved;

    @Version
    private long version;

    protected RoomTypeInventory() {
    }

    public RoomTypeInventory(RoomTypeInventoryId id, int totalInventory) {
        this.id = id;
        this.totalInventory = totalInventory;
        this.totalReserved = 0;
    }

    // TODO (madde 8): reserve(int rooms) / release(int rooms)

    public void reserve(int rooms) {
        if (rooms <= 0) {
            throw new IllegalArgumentException("Rooms must be greater than zero");
        }

        int capacity = totalInventory * OVERBOOKING_NUMERATOR / OVERBOOKING_DENOMINATOR;
        if ((capacity - this.totalReserved) < rooms) {
            throw new NoInventoryException("Overbookable rooms exceeded");
        }
        this.totalReserved += rooms;
    }

    public void release(int rooms) {
        if (rooms <= 0 || this.totalReserved < rooms) {
            throw new IllegalArgumentException("Rooms must be greater than zero");
        }
        this.totalReserved -= rooms;
    }

    public RoomTypeInventoryId getId() {
        return id;
    }

    public int getTotalInventory() {
        return totalInventory;
    }

    public int getTotalReserved() {
        return totalReserved;
    }

    public long getVersion() {
        return version;
    }
}
