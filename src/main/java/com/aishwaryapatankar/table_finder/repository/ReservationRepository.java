package com.aishwaryapatankar.table_finder.repository;

import com.aishwaryapatankar.table_finder.model.Reservation;
import com.aishwaryapatankar.table_finder.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByTableAndReservationTimeBetween(Table table, LocalDateTime startTime, LocalDateTime endTime);
}
