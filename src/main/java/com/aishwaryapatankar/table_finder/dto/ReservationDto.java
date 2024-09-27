package com.aishwaryapatankar.table_finder.dto;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ReservationDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "reservation_diners",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "diner_id")
    )
    private List<DinerDto> dinerDtos;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableDto table;

    private LocalDateTime reservationTime;

}