package com.bms.globalcustomvalidations.service.rules;

import com.bms.globalcustomvalidations.service.constants.BusinessMessages;
import com.bms.globalcustomvalidations.core.exceptions.BusinessException;
import com.bms.globalcustomvalidations.model.User;
import com.bms.globalcustomvalidations.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.List;

@Component
public class UserBusinessRules {
    private final UserRepository userRepository;

    public UserBusinessRules(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void checkIfUserExistsByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new BusinessException(BusinessMessages.USER_ALREADY_EXISTS_BY_EMAIL);
        }
    }

    public void checkIfUserExistByUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new BusinessException(BusinessMessages.USER_ALREADY_EXISTS_BY_USERNAME);
        }
    }

    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BusinessMessages.USER_NOT_FOUND_BY_ID));
    }

    public void getAllUsers(List<User> users) {
        if (users.isEmpty()) {
            throw new NotFoundException(BusinessMessages.USER_LIST_IS_EMPTY);
        }
    }
}
