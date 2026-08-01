package com.hotelres.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
