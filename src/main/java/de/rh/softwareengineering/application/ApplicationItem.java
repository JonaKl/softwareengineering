package de.rh.softwareengineering.application;

import de.rh.softwareengineering.hostuniversity.HostUniversity;
import lombok.Data;

@Data
public class ApplicationItem {
    HostUniversity hostUniversity;
    int priority;
    boolean admitted;

    public ApplicationItem(HostUniversity hostUniversity, int priority) {
        this.hostUniversity = hostUniversity;
        this.priority = priority;
        this.admitted = false;
    }
}
