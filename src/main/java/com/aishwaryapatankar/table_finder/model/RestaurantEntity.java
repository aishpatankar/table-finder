package com.aishwaryapatankar.table_finder.model;

import com.aishwaryapatankar.table_finder.dto.RestaurantDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

import java.util.List;

@Entity
@Builder
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private List<String> endorsements;

    public RestaurantDto convertToDto() {
        return RestaurantDto.builder()
                .endorsements(endorsements)
                .name(name)
                .id(id)
                .build();
    }
}