package com.aishwaryapatankar.table_finder.service;

import com.aishwaryapatankar.table_finder.dto.FindRestaurantRequest;
import com.aishwaryapatankar.table_finder.dto.MatchingRestaurants;
import com.aishwaryapatankar.table_finder.dto.RestaurantDto;
import com.aishwaryapatankar.table_finder.model.Reservation;
import com.aishwaryapatankar.table_finder.model.Restaurant;
import com.aishwaryapatankar.table_finder.model.Table;
import com.aishwaryapatankar.table_finder.repository.ReservationRepository;
import com.aishwaryapatankar.table_finder.repository.RestaurantRepository;
import com.aishwaryapatankar.table_finder.repository.TableRepository;
import org.hibernate.query.sqm.ParsingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        System.out.println("Dietary Restrictions num " + dietaryRestrictions.size());
        System.out.println("------------ break pt 1 -----------------------");

        List<Restaurant> compatibleRestaurantEntities = restaurantRepository.findRestaurantWithAllEndorsements(dietaryRestrictions, dietaryRestrictions.size());
        System.out.println("Found restaurants num " + compatibleRestaurantEntities.size());

        System.out.println("------------ break pt 2 -----------------------");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.parse(restaurantRequest.getTime(), formatter);
        System.out.println("------------ break pt 3 -----------------------");


        return buildRestaurantDto(compatibleRestaurantEntities.stream()
                .filter(restaurant -> hasAvailableTable(restaurant, restaurantRequest.getDiners().size(), time))
                .collect(Collectors.toList()));
    }
    private MatchingRestaurants buildRestaurantDto(List<Restaurant> restaurantEntities) throws ParsingException {
        List<RestaurantDto> matchingRestaurants = restaurantEntities.stream()
                .map(Restaurant::convertToDto)
                .collect(Collectors.toList());
        return MatchingRestaurants
                .builder()
                .restaurantList(matchingRestaurants).build();
    }

    private boolean hasAvailableTable(Restaurant restaurant, int partySize, LocalDateTime startTime) {
        List<Table> tableEntities = tableRepository.findByCapacityGreaterThanEqual(partySize);
        System.out.println("------------ break pt 4 -----------------------");

        LocalDateTime endTime = startTime.plusHours(2);

        return tableEntities.stream()
                .anyMatch(table -> {
                    System.out.println("------------ break pt 5 -----------------------");

                    List<Reservation> reservationEntities = reservationRepository.findByTableAndReservationTimeBetween(
                            table, startTime, endTime);
                    return reservationEntities.isEmpty();
                });
    }

}
