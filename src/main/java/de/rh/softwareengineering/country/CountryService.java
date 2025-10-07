package de.rh.softwareengineering.country;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public List<Country> getCountries() {
        List<Country> countries = countryRepository.findAll();
        System.out.println("countries size: " + countries.size());
        System.out.println("countries: " + countries);
        return countries;
    }
}
