package com.aishwaryapatankar.table_finder.controller;

import com.aishwaryapatankar.table_finder.dto.FindRestaurantRequest;
import com.aishwaryapatankar.table_finder.dto.MatchingRestaurants;
import com.aishwaryapatankar.table_finder.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping()
    public ResponseEntity<MatchingRestaurants> findAvailableRestaurants(@RequestBody FindRestaurantRequest findRestaurantRequest) {
        return new ResponseEntity<>(
                restaurantService.findAvailableRestaurants(findRestaurantRequest),
                HttpStatusCode.valueOf(200));
    }



}
