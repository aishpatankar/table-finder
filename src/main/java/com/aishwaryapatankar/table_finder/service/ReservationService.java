package com.aishwaryapatankar.table_finder.service;

import com.aishwaryapatankar.table_finder.dto.DinerDto;
import com.aishwaryapatankar.table_finder.dto.ReservationDto;
import com.aishwaryapatankar.table_finder.dto.ReservationRequest;
import com.aishwaryapatankar.table_finder.model.Diner;
import com.aishwaryapatankar.table_finder.model.Reservation;
import com.aishwaryapatankar.table_finder.model.Table;
import com.aishwaryapatankar.table_finder.repository.DinerRepository;
import com.aishwaryapatankar.table_finder.repository.ReservationRepository;
import com.aishwaryapatankar.table_finder.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class ReservationService {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private DinerRepository dinerRepository;

    public ReservationDto createReservation(ReservationRequest reservationRequest) {
        Table table = tableRepository.findById(reservationRequest.getTable().getId())
                .orElseThrow(() -> new RuntimeException("Table not found"));

        List<Long> dinerEntities = reservationRequest.getDiners().stream()
                .map(d -> d.getId())
                .collect(Collectors.toList());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.parse(reservationRequest.getTime(), formatter);

        Reservation reservation = Reservation.builder()
                .reservationDiners(dinerEntities)
                .table(table)
                .reservationTime(time)
                .build();

        Reservation saved = reservationRepository.save(reservation);

        return ReservationDto.builder()
                .id(saved.getId())
                .dinerDtos(reservationRequest.getDiners())
                .reservationTime(reservationRequest.getTime())
                .table(reservationRequest.getTable()).build();
    }
}
