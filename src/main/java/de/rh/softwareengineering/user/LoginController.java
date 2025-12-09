package de.rh.softwareengineering.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PersistenceContext // RTE injiziert den EM
    private EntityManager em;

    @GetMapping("/api/loadUser")
    public User loadUser(@RequestParam String userId, @RequestParam String password) {
        try {
            TypedQuery<User> query = em.createNamedQuery("User.loginUser", User.class);

            query.setParameter("userId", userId);
            query.setParameter("password", password);

            User userData = query.getSingleResult();
            return userData;
        } catch (NoResultException e) {
            logger.warn("No user found for userId: " + userId + " with the given password.");
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    @PostMapping("/api/registerUser")
    @Transactional
    public String registerUser(@RequestParam String userId, @RequestParam String password) {
        try {
            User newUser = new User();
            newUser.setUserId(userId);
            newUser.setPassword(password);
            em.persist(newUser);
            return "User registered successfully.";
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Error registering user.");
        }
    }
}


