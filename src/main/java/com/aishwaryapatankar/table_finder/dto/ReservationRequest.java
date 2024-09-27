package com.aishwaryapatankar.table_finder.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ReservationRequest {
    @NotEmpty(message = "Diner information missing")
    List<DinerDto> diners;

    @NotEmpty(message = "Restaurant information missing")
    RestaurantDto restaurant;

    @NotEmpty(message = "Table information missing")
    TableDto table;

    @NotEmpty(message = "Time missing")
    LocalDateTime time;
}
