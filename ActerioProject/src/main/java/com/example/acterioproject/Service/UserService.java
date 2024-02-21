package com.example.acterioproject.Service;
import com.example.acterioproject.Pojo.User;
import com.example.acterioproject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //Method for getting all users in DB, using repository
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean existsByEmail(String email) {
        // Check if a user with the given email exists in the database
        return userRepository.existsByEmail(email);
    }
    public User createUser(User user) {
        //Encodes the password
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    //Checks if given password matches old password
    public boolean authenticate(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            // Hash the input password and compare it with the stored hash
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false; // User not found or incorrect password
    }

    //Update the password, for the patch request. encodes the new password
    public void updatePasswordByEmail(String email, String newPassword) {
        // Find the user by email
        User user = userRepository.findByEmail(email);

        // Update the user's password
        user.setPassword(passwordEncoder.encode(newPassword));

        // Save the updated user entity back to the database
        userRepository.save(user);
    }

    //For the put request, changes the email and the password.
    public void updateUser(String email, String newEmail, String newPassword) {
        // Find the user by email
        User user = userRepository.findByEmail(email);

        user.setEmail(newEmail);
        user.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);
    }


    //For the delete request
    public void deleteUserByEmail(String email) {
        // Find the user by email
        User user = userRepository.findByEmail(email);

        // Delete the user
        userRepository.delete(user);
    }
}