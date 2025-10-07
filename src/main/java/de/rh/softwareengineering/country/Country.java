package de.rh.softwareengineering.country;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Country {
    @Id
    String countryName;
}
