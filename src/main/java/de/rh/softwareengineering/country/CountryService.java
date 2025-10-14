package de.rh.softwareengineering.country;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public String getCountry() {
        Country country = countryRepository.findAll().getFirst();
        System.out.println("country: " + country.getCountryName());
        return country.getCountryName();
    }
}
