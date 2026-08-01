package com.hotelres.inventory;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class RoomTypeInventoryId implements Serializable {

    private Long hotelId;

    private Long roomTypeId;

    private LocalDate inventoryDate;

    protected RoomTypeInventoryId() {
    }

    public RoomTypeInventoryId(Long hotelId, Long roomTypeId, LocalDate inventoryDate) {
        this.hotelId = hotelId;
        this.roomTypeId = roomTypeId;
        this.inventoryDate = inventoryDate;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public LocalDate getInventoryDate() {
        return inventoryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoomTypeInventoryId other)) {
            return false;
        }
        return Objects.equals(hotelId, other.hotelId)
                && Objects.equals(roomTypeId, other.roomTypeId)
                && Objects.equals(inventoryDate, other.inventoryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId, roomTypeId, inventoryDate);
    }

    @Override
    public String toString() {
        return "RoomTypeInventoryId[hotelId=" + hotelId
                + ", roomTypeId=" + roomTypeId
                + ", inventoryDate=" + inventoryDate + "]";
    }
}
