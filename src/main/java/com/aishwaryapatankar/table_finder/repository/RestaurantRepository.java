package com.aishwaryapatankar.table_finder.repository;

import com.aishwaryapatankar.table_finder.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query(value = "SELECT r.* FROM restaurants r " +
            "JOIN restaurant_endorsements re ON r.id = re.restaurant_id " +
            "WHERE re.endorsement_name IN (:requiredEndorsements) " +
            "GROUP BY r.id " +
            "HAVING COUNT(DISTINCT re.endorsement_name) = :endorsementCount",
            nativeQuery = true)
    List<Restaurant> findRestaurantWithAllEndorsements(@Param("requiredEndorsements") List<String> requiredEndorsements, @Param("endorsementCount") long endorsementCount);
}
