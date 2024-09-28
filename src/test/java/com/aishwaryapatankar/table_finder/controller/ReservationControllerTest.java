package com.aishwaryapatankar.table_finder.controller;

import com.aishwaryapatankar.table_finder.dto.DinerDto;
import com.aishwaryapatankar.table_finder.dto.ReservationRequest;
import com.aishwaryapatankar.table_finder.dto.RestaurantDto;
import com.aishwaryapatankar.table_finder.dto.TableDto;
import com.aishwaryapatankar.table_finder.model.Diner;
import com.aishwaryapatankar.table_finder.service.ReservationService;
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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();
    }

    @Test
    public void givenDinerAndRestaurantExistAndTableIsAvailable_createReservationWith200() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        List<DinerDto> dinerDtoList = List.of(DinerDto.builder().id(1L).name("Aishwarya").dietaryRestrictions(List.of("Vegetarian")).build());
        List<Diner> dinerEntitiesList = List.of(dinerDtoList.get(0).convertToEntity());
        RestaurantDto restaurantDto = RestaurantDto.builder().id(1L).name("Veggie Grill").endorsements(List.of("Vegetarian")).build();
        TableDto tableDto = TableDto.builder().id(1L).capacity(2).restaurant(restaurantDto).build();

        String request = objectMapper.writeValueAsString(ReservationRequest.builder()
                .diners(dinerDtoList)
                .table(tableDto)
                .time("2024-09-27 14:45:30")
                .build());

        mockMvc.perform(MockMvcRequestBuilders.
                        post("/api/reservations/create")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}
