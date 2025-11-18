package de.rh.softwareengineering.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void checkForCreatedStudent() {
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student = studentRepository.save(student);
        System.out.println("Created student ID: " + student.getId());
        Student createdStudent = studentRepository.findById(student.getId()).orElse(null);
        assertNotNull(createdStudent);
        assertEquals("John", createdStudent.getFirstName());
        assertEquals("Doe", createdStudent.getLastName());
    }
}