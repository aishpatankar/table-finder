package com.aishwaryapatankar.table_finder.repository;

import com.aishwaryapatankar.table_finder.model.RestaurantEndorsement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantEndorsementRepository extends JpaRepository<RestaurantEndorsement, Long> {
    List<RestaurantEndorsement> findAllByRestaurantId(Long restaurantId);

}
