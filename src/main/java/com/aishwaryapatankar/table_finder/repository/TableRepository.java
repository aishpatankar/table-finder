package com.aishwaryapatankar.table_finder.repository;

import com.aishwaryapatankar.table_finder.model.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableRepository extends JpaRepository<TableEntity, Long> {

    List<TableEntity> findByCapacityGreaterThanEqual(Integer capacity);
}
