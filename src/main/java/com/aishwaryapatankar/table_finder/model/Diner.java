package com.aishwaryapatankar.table_finder.model;

import com.aishwaryapatankar.table_finder.dto.DinerDto;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Table(name = "diners")
@AllArgsConstructor
@NoArgsConstructor
public class Diner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(name = "dietary_restrictions")
    private List<String> dietaryRestrictions;

}