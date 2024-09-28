package com.aishwaryapatankar.table_finder.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table(name = "restaurant_endorsements")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RestaurantEndorsement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(name = "endorsement_name")
    private String endorsementName;
}