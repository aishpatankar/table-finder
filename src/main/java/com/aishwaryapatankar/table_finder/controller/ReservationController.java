package com.aishwaryapatankar.table_finder.controller;

import com.aishwaryapatankar.table_finder.dto.ReservationDto;
import com.aishwaryapatankar.table_finder.dto.ReservationRequest;
import com.aishwaryapatankar.table_finder.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/create")
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationRequest reservationRequest) {
        return new ResponseEntity<>(reservationService.createReservation(reservationRequest), HttpStatusCode.valueOf(200));
    }

}
