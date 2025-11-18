package de.rh.softwareengineering.country;

import de.rh.softwareengineering.hostuniversity.HostUniversity;
import de.rh.softwareengineering.hostuniversity.HostUniversityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CountryTest {
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private HostUniversityRepository hostUniversityRepository;

    private Country country;

    @BeforeEach
    void setUp() {
        country = new Country();
        country.setCountryName("Country 1");
        country.setUniversityList(List.of());
        country = countryRepository.save(country);
    }

    @Test
    void checkForCreatedCountry() {
        Country createdCountry = countryRepository.findByCountryName("Country 1").orElse(null);
        assertThat(createdCountry)
                .isNotNull();
        assertThat(createdCountry.getCountryName())
                .isNotNull()
                .isEqualTo("Country 1");
    }

    @Test
    void addUniversityToCountry() {
        HostUniversity hostUniversity = new HostUniversity();
        hostUniversity.setCity("City 1");
        hostUniversity.setName("University 1");
        hostUniversity = hostUniversityRepository.save(hostUniversity);
        List<HostUniversity> hostUniversityList = country.getUniversityList();
        hostUniversityList.add(hostUniversity);
        country.setUniversityList(hostUniversityList);
        Country updatedCountry = countryRepository.save(country);
        assertThat(updatedCountry)
                .isNotNull();
        assertThat(updatedCountry.getUniversityList())
                .isNotNull()
                .hasSize(1)
                .contains(hostUniversity);
    }
}
