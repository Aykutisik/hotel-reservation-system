package com.hotelres.web;


import com.hotelres.inventory.NoInventoryException;
import com.hotelres.reservation.ReservationAccessDeniedException;
import com.hotelres.reservation.ReservationNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Tum controller'lar icin ortak hata esleme. Hicbir ozellik paketine ait degil.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /** Rezervasyon bulunamadi. */
    @ExceptionHandler(ReservationNotFoundException.class)
    ResponseEntity<Void> notFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();      // 404
    }

    /** Rezervasyon baska bir misafire ait. */
    @ExceptionHandler(ReservationAccessDeniedException.class)
    ResponseEntity<Void> accessDenied() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();      // 403
    }

    /** Ayni reservationId ikinci kez geldi -- rezervasyon zaten var. */
    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<Void> duplicate() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();       // 409
    }

    /** O tarihlerde yer yok. */
    @ExceptionHandler(NoInventoryException.class)
    ResponseEntity<Void> noInventory() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();       // 409
    }

    /** Zaten iptal edilmis -- iptal idempotent, hata degil. */
    @ExceptionHandler(IllegalStateException.class)
    ResponseEntity<Void> alreadyCanceled() {
        return ResponseEntity.noContent().build();                       // 204
    }
}
