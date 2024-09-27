package com.aishwaryapatankar.table_finder.model;

import com.aishwaryapatankar.table_finder.dto.DinerDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

import java.util.List;

@Entity
@Builder
public class DinerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private List<String> dietaryRestrictions;

    public DinerDto convertToDto() {
        return DinerDto
                .builder()
                .id(id)
                .name(name)
                .dietaryRestrictions(dietaryRestrictions)
                .build();
    }

}