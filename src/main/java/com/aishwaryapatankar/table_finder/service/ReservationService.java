package com.aishwaryapatankar.table_finder.service;

import com.aishwaryapatankar.table_finder.dto.DinerDto;
import com.aishwaryapatankar.table_finder.dto.ReservationDto;
import com.aishwaryapatankar.table_finder.dto.ReservationRequest;
import com.aishwaryapatankar.table_finder.model.DinerEntity;
import com.aishwaryapatankar.table_finder.model.ReservationEntity;
import com.aishwaryapatankar.table_finder.model.TableEntity;
import com.aishwaryapatankar.table_finder.repository.ReservationRepository;
import com.aishwaryapatankar.table_finder.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class ReservationService {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public ReservationDto createReservation(ReservationRequest reservationRequest) {
        TableEntity table = tableRepository.findById(reservationRequest.getTable().getId())
                .orElseThrow(() -> new RuntimeException("Table not found"));

        List<DinerEntity> dinerEntities = reservationRequest.getDiners().stream()
                .map(DinerDto::convertToEntity)
                .collect(Collectors.toList());

        ReservationEntity reservationEntity = ReservationEntity.builder()
                .dinerEntities(dinerEntities)
                .tableEntity(table)
                .reservationTime(reservationRequest.getTime())
                .build();

        reservationRepository.save(reservationEntity);
        return reservationEntity.convertToDto();
    }
}
