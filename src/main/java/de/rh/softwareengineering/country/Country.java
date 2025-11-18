package de.rh.softwareengineering.country;

import de.rh.softwareengineering.hostuniversity.HostUniversity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Country {
    @Id
    private String countryName;

    @OneToMany(fetch = FetchType.EAGER)
    private List<HostUniversity>  universityList;

    public Country(String countryName) {
        this.countryName = countryName;
        this.universityList = new ArrayList<>();
    }
}
