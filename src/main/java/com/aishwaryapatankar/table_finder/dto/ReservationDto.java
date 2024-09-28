package com.aishwaryapatankar.table_finder.dto;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    private String reservationTime;

}