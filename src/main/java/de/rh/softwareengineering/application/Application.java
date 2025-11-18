package de.rh.softwareengineering.application;

import de.rh.softwareengineering.hostuniversity.HostUniversity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Application {
    String homeSemester;
    String hostSemester;
    String languageLevel;
    String reason;
    List<ApplicationItem> applicationItems;

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
