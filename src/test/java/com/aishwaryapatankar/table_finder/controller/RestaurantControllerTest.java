package com.aishwaryapatankar.table_finder.controller;


import com.aishwaryapatankar.table_finder.dto.DinerDto;
import com.aishwaryapatankar.table_finder.dto.FindRestaurantRequest;
import com.aishwaryapatankar.table_finder.dto.MatchingRestaurants;
import com.aishwaryapatankar.table_finder.dto.RestaurantDto;
import com.aishwaryapatankar.table_finder.service.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    private RestaurantController restaurantController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(restaurantController).build();
    }

    @Test
    public void givenRestaurantExistsOfDietaryPreferenceMatchingDinersAndNoOtherBookings_returnRestaurantWith200() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        MatchingRestaurants mockedRestaurantList = MatchingRestaurants
                .builder()
                .restaurantList(List.of(RestaurantDto.builder()
                                .endorsements(List.of("Vegetarian"))
                                .name("Veggie Grill")
                                .id(1L)
                        .build()))
                .build();

        when(restaurantService.findAvailableRestaurants(any())).thenReturn(mockedRestaurantList);

        String request = objectMapper.writeValueAsString(FindRestaurantRequest.builder()
                .diners(List.of(DinerDto.builder().name("Mike").dietaryRestrictions(List.of("Vegetarian")).build()))
                .time("2024-09-27 14:45:30")
                .build());

        mockMvc.perform(MockMvcRequestBuilders.
                        get("/api/restaurants")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.restaurantList[0].name")
                        .value("Veggie Grill"));

    }
}
