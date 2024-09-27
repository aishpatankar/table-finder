package com.aishwaryapatankar.table_finder.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
public class FindRestaurantRequest {
    @NotEmpty(message = "Diner information missing")
    List<DinerDto> diners;

    @NotEmpty(message = "time missing")
    LocalDateTime time;
}
