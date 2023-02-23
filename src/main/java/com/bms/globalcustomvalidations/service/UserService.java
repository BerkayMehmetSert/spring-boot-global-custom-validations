package com.bms.globalcustomvalidations.service;

import com.bms.globalcustomvalidations.service.constants.ValidationMessages;
import com.bms.globalcustomvalidations.core.utilities.security.PasswordEncoder;
import com.bms.globalcustomvalidations.core.utilities.services.rules.BusinessRules;
import com.bms.globalcustomvalidations.core.utilities.services.rules.ValidationRules;
import com.bms.globalcustomvalidations.model.User;
import com.bms.globalcustomvalidations.repository.UserRepository;
import com.bms.globalcustomvalidations.service.request.CreateUserRequest;
import com.bms.globalcustomvalidations.service.request.UpdateUserRequest;
import com.bms.globalcustomvalidations.service.rules.UserBusinessRules;
import com.bms.globalcustomvalidations.service.validation.UserValidations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserBusinessRules userBusinessRules;
    private final UserValidations userValidations;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserBusinessRules userBusinessRules,
                       UserValidations userValidations, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userBusinessRules = userBusinessRules;
        this.userValidations = userValidations;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(CreateUserRequest request) {
        ValidationRules.run(() -> {
            userValidations.notEmpty(request.getUsername(), ValidationMessages.USERNAME_IS_REQUIRED);
            userValidations.minLength(request.getUsername(), 3, ValidationMessages.USERNAME_MIN_LENGTH);
            userValidations.notEmpty(request.getPassword(), ValidationMessages.PASSWORD_IS_REQUIRED);
            userValidations.minLength(request.getPassword(), 6, ValidationMessages.PASSWORD_MIN_LENGTH);
            userValidations.notEmpty(request.getEmail(), ValidationMessages.EMAIL_IS_REQUIRED);
            userValidations.password(
                    request.getPassword(),
                    ValidationMessages.EMAIL_REGEX,
                    ValidationMessages.PASSWORD_IS_NOT_VALID
            );
            userValidations.email(
                    request.getEmail(),
                    ValidationMessages.PASSWORD_REGEX,
                    ValidationMessages.EMAIL_IS_NOT_VALID
            );
        });

        BusinessRules.run(() -> {
            userBusinessRules.checkIfUserExistByUsername(request.getUsername());
            userBusinessRules.checkIfUserExistsByEmail(request.getEmail());
        });

        var user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());

        userRepository.save(user);
    }

    public void updateUser(int id, UpdateUserRequest request) {
        var user = BusinessRules.run(() -> {
            return userBusinessRules.getUserById(id);
        });

        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());

        userRepository.save(user);
    }

    public void deleteUser(int id) {
        BusinessRules.run(() -> {
            userBusinessRules.getUserById(id);
        });

        userRepository.deleteById(id);
    }

    public User getUserById(int id) {
        return BusinessRules.run(() -> {
            return userBusinessRules.getUserById(id);
        });
    }

    public List<User> getAllUsers() {
        var users = userRepository.findAll();

        BusinessRules.run(() -> {
            userBusinessRules.getAllUsers(users);
        });

        return users;
    }
}
