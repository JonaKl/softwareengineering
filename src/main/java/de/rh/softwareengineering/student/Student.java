package de.rh.softwareengineering.student;

import de.rh.softwareengineering.application.Application;
import de.rh.softwareengineering.application.ApplicationItem;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Student {
    @TableGenerator(
            name = "student_gen",
            table = "id_gen",
            pkColumnName = "gen_name",
            valueColumnName = "gen_value",
            pkColumnValue = "student_id",
            initialValue = 100100,
            allocationSize = 1
    )
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "student_gen")
    private int studentId;

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "student")
    private List<Application> applications;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.applications = new ArrayList<>();
    }

    public List<ApplicationItem> getApprovedApplicationItems() {
        List<ApplicationItem> approvedItems = new ArrayList<>();
        for (Application application : applications) {
            approvedItems.addAll(application.getApprovedApplicationItems());
        }
        return approvedItems;
    }

    public Application createApplication(String homeSemester, String hostSemester, String languageLevel, String reason) {
        Application application = new Application(homeSemester, hostSemester, languageLevel, reason);
        this.applications.add(application);
        return application;
    }
}
