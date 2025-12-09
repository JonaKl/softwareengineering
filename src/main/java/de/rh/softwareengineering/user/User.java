package de.rh.softwareengineering.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "UserData")
@Data
@NamedQuery(name = "User.loginUser", // frei wählbar, aber Konvention --> Selbstdoku
        query = "Select u from User u where u.userId = :userId and u.password = :password") // JPQL

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String userId;
    private String password;

}