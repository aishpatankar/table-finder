package com.aishwaryapatankar.table_finder.dto;

import com.aishwaryapatankar.table_finder.model.RestaurantEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private List<String> endorsements;

    public RestaurantEntity convertToEntity() {
        return RestaurantEntity.builder()
                .id(id)
                .name(name)
                .endorsements(endorsements)
                .build();
    }
}