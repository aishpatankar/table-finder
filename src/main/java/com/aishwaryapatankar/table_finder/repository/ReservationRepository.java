package com.aishwaryapatankar.table_finder.repository;

import com.aishwaryapatankar.table_finder.model.ReservationEntity;
import com.aishwaryapatankar.table_finder.model.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    List<ReservationEntity> findByTableEntityAndReservationTimeBetween(TableEntity tableEntity, LocalDateTime startTime, LocalDateTime endTime);
}
