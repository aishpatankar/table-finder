package com.aishwaryapatankar.table_finder.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Builder
public class MatchingRestaurants {
    List<RestaurantDto> restaurantList;
}
