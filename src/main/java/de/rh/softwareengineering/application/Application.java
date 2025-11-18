package de.rh.softwareengineering.application;

import de.rh.softwareengineering.hostuniversity.HostUniversity;
import de.rh.softwareengineering.student.Student;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String homeSemester;
    private String hostSemester;
    private String languageLevel;
    private String reason;
    @OneToMany(mappedBy = "application", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<ApplicationItem> applicationItems;
    @Temporal(TemporalType.DATE)
    private Date dateOfCreation = new Date();
    @ManyToOne
    private Student student;

    public Application(String homeSemester, String hostSemester, String languageLevel, String reason) {
        this.homeSemester = homeSemester;
        this.hostSemester = hostSemester;
        this.languageLevel = languageLevel;
        this.reason = reason;
        this.applicationItems = new ArrayList<>();
    }



    public void createApplicationItem(HostUniversity hostUniversity, int priority) {
        this.applicationItems.add(new ApplicationItem(hostUniversity, priority));
    }

    public ArrayList<ApplicationItem> getApprovedApplicationItems() {
        ArrayList<ApplicationItem> approvedItems = new ArrayList<>();
        for (ApplicationItem item : applicationItems) {
            if (item.isAdmitted()) {
                approvedItems.add(item);
            }
        }
        return approvedItems;
    }
}
