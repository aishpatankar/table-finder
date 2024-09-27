package com.aishwaryapatankar.table_finder.model;

import com.aishwaryapatankar.table_finder.dto.ReservationDto;
import com.aishwaryapatankar.table_finder.dto.TableDto;
import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Builder
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableEntity tableEntity;

    @ManyToMany
    @JoinTable(
            name = "reservation_diners",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "diner_id")
    )
    private List<DinerEntity> dinerEntities;

    private LocalDateTime reservationTime;

    public ReservationDto convertToDto() {
        return ReservationDto
                .builder()
                .id(id)
                .table(
                        TableDto
                                .builder()
                                .id(tableEntity.getId())
                                .capacity(tableEntity.getCapacity())
                                .restaurant(tableEntity.getRestaurant().convertToDto())
                                .build()
                )
                .dinerDtos(dinerEntities.stream().map(DinerEntity::convertToDto).collect(Collectors.toList()))
                .reservationTime(reservationTime)
                .build();
    }

}