package com.aishwaryapatankar.table_finder.dto;


import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchingRestaurants {
    List<RestaurantDto> restaurantList;
}
