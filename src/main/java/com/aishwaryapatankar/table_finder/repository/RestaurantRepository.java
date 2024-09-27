package com.aishwaryapatankar.table_finder.repository;

import com.aishwaryapatankar.table_finder.model.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

    List<RestaurantEntity> findByEndorsementsIn(List<String> endorsements);
}
