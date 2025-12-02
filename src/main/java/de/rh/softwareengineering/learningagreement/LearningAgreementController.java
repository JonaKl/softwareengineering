package de.rh.softwareengineering.learningagreement;

import de.rh.softwareengineering.application.ApplicationItem;
import de.rh.softwareengineering.student.Student;
import jakarta.persistence.EntityManager;

import java.util.List;

public class LearningAgreementController {
    public List<ApplicationItem> getApprovedApplicationItems(Student student) {
        return student.getApprovedApplicationItems();
    }
}
