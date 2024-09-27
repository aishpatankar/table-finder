package com.aishwaryapatankar.table_finder.repository;

import com.aishwaryapatankar.table_finder.model.DinerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DinerRepository extends JpaRepository<DinerEntity, Long> {
}
