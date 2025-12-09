package de.rh.softwareengineering.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDTO toUserResponseDTO(User user) {
        return new UserResponseDTO(user.getUserId());
    }
}

