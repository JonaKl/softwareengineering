package de.rh.softwareengineering.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PersistenceContext // RTE injiziert den EM
    private EntityManager em;

    private final UserMapper userMapper;

    @PostMapping("/api/loadUser")
    public ResponseEntity<UserResponseDTO> loadUser(@RequestBody UserCreationDTO userCreationDTO) {
        try {
            TypedQuery<User> query = em.createNamedQuery("User.loginUser", User.class);

            query.setParameter("userId", userCreationDTO.getUserId());
            query.setParameter("password", userCreationDTO.getPassword());

            User userData = query.getSingleResult();
            return ResponseEntity.ok(userMapper.toUserResponseDTO(userData));
        } catch (NoResultException e) {
            logger.warn("No user found for userId: " + userCreationDTO.getUserId() + " with the given password .");
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/api/registerUser")
    @Transactional
    public ResponseEntity<String> registerUser(@RequestBody UserCreationDTO userCreationDTO) {
        try {
            User newUser = new User();
            newUser.setUserId(userCreationDTO.getUserId());
            newUser.setPassword(userCreationDTO.getPassword());
            em.persist(newUser);
            return ResponseEntity.ok("User registered successfully.");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().body("Error registering user.");
        }
    }
}


