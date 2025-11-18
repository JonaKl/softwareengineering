package de.rh.softwareengineering.student;

import de.rh.softwareengineering.application.Application;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
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
    private int id;

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "student")
    private List<Application> applications;
}
