package de.rh.softwareengineering.hostuniversity;

import de.rh.softwareengineering.country.Country;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HostUniversity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Country country;

    private String name;

    private String city;

    public HostUniversity(Country country, String name, String city) {
        this.country = country;
        this.name = name;
        this.city = city;
    }
}
