package com.hotelres.inventory;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "room_type_inventory")
public class RoomTypeInventory {

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
