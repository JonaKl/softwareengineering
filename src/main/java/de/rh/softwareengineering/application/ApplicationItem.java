package de.rh.softwareengineering.application;

import de.rh.softwareengineering.hostuniversity.HostUniversity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class ApplicationItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private HostUniversity hostUniversity;
    @ManyToOne
    private Application application;
    private int priority;
    private boolean admitted;

    public ApplicationItem(HostUniversity hostUniversity, int priority) {
        this.hostUniversity = hostUniversity;
        this.priority = priority;
        this.admitted = false;
    }
}
