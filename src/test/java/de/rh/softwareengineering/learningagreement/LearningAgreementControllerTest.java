package de.rh.softwareengineering.learningagreement;

import ch.qos.logback.classic.Logger;
import de.rh.softwareengineering.application.Application;
import de.rh.softwareengineering.application.ApplicationItem;
import de.rh.softwareengineering.country.Country;
import de.rh.softwareengineering.hostuniversity.HostUniversity;
import de.rh.softwareengineering.student.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@DataJpaTest
public class LearningAgreementControllerTest {
    @Autowired
    private TestEntityManager em;

    private Student student;
    private LearningAgreementController controllerInstance;

    @BeforeEach
    public void setUp() {
        student = new Student("Bosch", "Hugo");
        em.persist(student);
        Country usa = new Country("USA");
        em.persist(usa);
        Country slovenia = new Country("Slovenia");
        em.persist(slovenia);
        HostUniversity wyoming = new HostUniversity(usa, "University of Wyoming", "Laramie");
        em.persist(wyoming);
        HostUniversity ljubljana = new HostUniversity(slovenia, "University of Ljubljana", "LJubljana");
        em.persist(ljubljana);
        Application application1 = student.createApplication("WS 2019/2020", "WS 2019/2020", "C1",
                "Strengthen intercultural skills");
        Application application2 = student.createApplication("WS 2019/2020", "WS 2019/2020", "C1",
                "Strengthen intercultural skills");
        application1.createApplicationItem(wyoming, 1);
        application1.createApplicationItem(ljubljana, 2);
        application2.createApplicationItem(ljubljana, 1);
        application2.createApplicationItem(wyoming, 2);
        em.persist(application1);
        em.persist(application2);
        em.flush();
        controllerInstance = new LearningAgreementController();
    }

    @Test
    public void testGetApprovedApplicationItemsNoApplicationsDB() {
        student = new Student("Boss", "Robert");
        em.persist(student);
        log.info("no Applications are created for this student");
        int expectedNumberOfItems = 0;
        check(expectedNumberOfItems);
        Student s = em.find(Student.class, student.getStudentId());
        // was this student really stored in db?
        assertNotNull(s);
        assertEquals(s.getLastName(), student.getLastName());
    }

    private void check(int expectedNumberOfItems) {
        List<ApplicationItem> result = controllerInstance.getApprovedApplicationItems(student);
        assertEquals(expectedNumberOfItems, result.size());
    }
}
