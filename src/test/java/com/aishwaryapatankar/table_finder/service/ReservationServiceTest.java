package com.aishwaryapatankar.table_finder.service;

import com.aishwaryapatankar.table_finder.controller.ReservationController;
import com.aishwaryapatankar.table_finder.dto.ReservationRequest;
import com.aishwaryapatankar.table_finder.repository.ReservationRepository;
import com.aishwaryapatankar.table_finder.repository.TableRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private TableRepository tableRepository;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reservationService).build();
    }

    @Test
    void deserializesRequestCorrectly() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInput = "{\n" +
                "  \"diners\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"Michael\",\n" +
                "      \"dietaryRestrictions\": [\n" +
                "        \"Vegetarian\"\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"table\": {\n" +
                "    \"id\": 1,\n" +
                "    \"capacity\": 2,\n" +
                "    \"restaurant\": {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"Lardo\",\n" +
                "      \"endorsements\": [\n" +
                "        \"Gluten Free Options\"\n" +
                "      ]\n" +
                "    }\n" +
                "  },\n" +
                "  \"time\": \"2024-09-27 14:45:30\"\n" +
                "}";
        ReservationRequest request = objectMapper.readValue(jsonInput, ReservationRequest.class);
    }
}