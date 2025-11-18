package de.rh.softwareengineering.hostuniversity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HostUniversityService {
    private final HostUniversityRepository hostUniversityRepository;

    public String getHostUniversity() {
        HostUniversity hostUniversity = hostUniversityRepository.findAll().getFirst();
        return hostUniversity.getName();
    }
}
