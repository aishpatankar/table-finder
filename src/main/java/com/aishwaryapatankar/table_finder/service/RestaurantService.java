package com.aishwaryapatankar.table_finder.service;

import com.aishwaryapatankar.table_finder.dto.FindRestaurantRequest;
import com.aishwaryapatankar.table_finder.dto.MatchingRestaurants;
import com.aishwaryapatankar.table_finder.dto.RestaurantDto;
import com.aishwaryapatankar.table_finder.model.ReservationEntity;
import com.aishwaryapatankar.table_finder.model.RestaurantEntity;
import com.aishwaryapatankar.table_finder.model.TableEntity;
import com.aishwaryapatankar.table_finder.repository.ReservationRepository;
import com.aishwaryapatankar.table_finder.repository.RestaurantRepository;
import com.aishwaryapatankar.table_finder.repository.TableRepository;
import org.hibernate.query.sqm.ParsingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    TableRepository tableRepository;
    @Autowired
    ReservationRepository reservationRepository;

    public MatchingRestaurants findAvailableRestaurants(FindRestaurantRequest restaurantRequest) {
        List<String> dietaryRestrictions = restaurantRequest.getDiners()
                .stream()
                .flatMap(diner -> diner.getDietaryRestrictions().stream())
                .distinct()
                .collect(Collectors.toList());

        List<RestaurantEntity> compatibleRestaurantEntities = restaurantRepository.findByEndorsementsIn(dietaryRestrictions);

        return buildRestaurantDto(compatibleRestaurantEntities.stream()
                .filter(restaurant -> hasAvailableTable(restaurant, restaurantRequest.getDiners().size(), restaurantRequest.getTime()))
                .collect(Collectors.toList()));
    }
    private MatchingRestaurants buildRestaurantDto(List<RestaurantEntity> restaurantEntities) throws ParsingException {
        List<RestaurantDto> matchingRestaurants = restaurantEntities.stream()
                .map(RestaurantEntity::convertToDto)
                .collect(Collectors.toList());
        return MatchingRestaurants
                .builder()
                .restaurantList(matchingRestaurants).build();
    }

    private boolean hasAvailableTable(RestaurantEntity restaurantEntity, int partySize, LocalDateTime startTime) {
        List<TableEntity> tableEntities = tableRepository.findByCapacityGreaterThanEqual(partySize);
        LocalDateTime endTime = startTime.plusHours(2);

        return tableEntities.stream()
                .anyMatch(table -> {
                    List<ReservationEntity> reservationEntities = reservationRepository.findByTableEntityAndReservationTimeBetween(
                            table, startTime, endTime);
                    return reservationEntities.isEmpty();
                });
    }

}
