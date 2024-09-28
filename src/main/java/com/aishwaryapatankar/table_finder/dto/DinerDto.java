package com.aishwaryapatankar.table_finder.dto;

import com.aishwaryapatankar.table_finder.model.Diner;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DinerDto {
    private Long id;

    private String name;

    private List<String> dietaryRestrictions;

    public Diner convertToEntity() {
        return Diner
                .builder()
                .id(id)
                .name(name)
                .dietaryRestrictions(dietaryRestrictions)
                .build();
    }
}