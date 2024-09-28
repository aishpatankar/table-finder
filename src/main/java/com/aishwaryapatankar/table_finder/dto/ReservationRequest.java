package com.aishwaryapatankar.table_finder.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {
    @NotEmpty(message = "Diner information missing")
    List<DinerDto> diners;

    @NotEmpty(message = "Table information missing")
    TableDto table;

    @NotEmpty(message = "Time missing")
    String time;
}
