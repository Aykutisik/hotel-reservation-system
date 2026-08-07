package com.hotelres.reservation;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> reserve(@RequestBody ReservationCommand cmd) {
        Reservation reservation = reservationService.reserve(cmd);
        return ResponseEntity
                .created(URI.create("/v1/reservations/" + reservation.getReservationId()))
                .body(ReservationResponse.from(reservation));
    }

    /**
     * TODO: guestId simdilik istekten geliyor -- gercek yetkilendirme degil.
     * Gercek kurulumda kimlik API Gateway tarafindan dogrulanip iletilir.
     */
    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> cancel(
            @PathVariable String reservationId,
            @RequestParam Long guestId) {
        reservationService.cancel(reservationId, guestId);
        return ResponseEntity.noContent().build();
    }
}
