package com.aishwaryapatankar.table_finder.repository;

import com.aishwaryapatankar.table_finder.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableRepository extends JpaRepository<Table, Long> {

    List<Table> findByCapacityGreaterThanEqual(Integer capacity);
}
