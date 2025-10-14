package de.rh.softwareengineering.country;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    String countryName;
}
