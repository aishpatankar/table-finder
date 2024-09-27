package com.aishwaryapatankar.table_finder.dto;

import com.aishwaryapatankar.table_finder.model.DinerEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class DinerDto {
    private Long id;

    private String name;

    private List<String> dietaryRestrictions;

    public DinerEntity convertToEntity() {
        return DinerEntity
                .builder()
                .id(id)
                .name(name)
                .dietaryRestrictions(dietaryRestrictions)
                .build();
    }
}