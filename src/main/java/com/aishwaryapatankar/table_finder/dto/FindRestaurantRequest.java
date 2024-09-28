package com.aishwaryapatankar.table_finder.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;


import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindRestaurantRequest {
    @NotEmpty(message = "Diner information missing")
    List<DinerDto> diners;

    @NotEmpty(message = "time missing")
    String time;
}
