package com.hotelres.reservation;


import com.hotelres.inventory.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/v1/reservations")
public class ReservationController {

    private final InventoryService inventoryService;

    public ReservationController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> reserve(@RequestBody ReservationCommand cmd) {
        Reservation reservation = inventoryService.reserve(cmd);
        return ResponseEntity
                .created(URI.create("/v1/reservations/" + reservation.getReservationId()))
                .body(ReservationResponse.from(reservation));
    }
}
