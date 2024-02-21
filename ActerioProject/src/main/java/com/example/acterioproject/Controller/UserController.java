package com.example.acterioproject.Controller;
import com.example.acterioproject.Pojo.User;
import com.example.acterioproject.Requests.DeleteRequest;
import com.example.acterioproject.Requests.UpdatePasswordRequest;
import com.example.acterioproject.Requests.UpdateUserRequest;
import com.example.acterioproject.Repository.UserRepository;
import com.example.acterioproject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    //Get all users in out DB
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    //Post for creating a user, requires a body from user class
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        // Check if the email already exists, return a conflict status
        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Email already exists");
        }

        // If the email doesn't exist, create the user, return a  201 created status
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdUser);
    }


    //Check for match based on email and password, requires body from user class
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticateUser(@RequestBody User user) {
        //Used authenticate method from service which returns a boolean
        boolean authenticated = userService.authenticate( user.getEmail(),  user.getPassword());
        if (authenticated) {
            return ResponseEntity.ok("Authentication successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }


    //Create a regex for finding email patterns
    private static final Pattern EMAIL_PATTERN = Pattern.compile("@([\\w.]+)");
    @Autowired
    private UserRepository userRepository;

    //Start by getting all emails, create a hashmap that stores emails and occurrences
    @GetMapping("/domainCounts")
    //returns a hashMap with values of occurrences for domain
    public Map<String, Integer> getDomainCounts() {
        //Find all emails from sql query in repository
        List<String> userEmails = userRepository.findAllEmails();
        //Create the hashmap
        Map<String, Integer> domainCounts = new HashMap<>();

        //Loop through,use regex to extract domain, put them in hashmap, increment if existing
        for (String email : userEmails) {
            String domain = extractDomain(email);
            domainCounts.put(domain, domainCounts.getOrDefault(domain, 0) + 1);
        }

        //Return hashmap
        return domainCounts;
    }

    //Will match string part with the regex, if found we will return the string that is catched
    private String extractDomain(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    //Patch request to change password, to change the password you will need to give the old password, created a updatePassword class for body
    @PatchMapping("/{email}")
    public ResponseEntity<String> updateUserPassword(@PathVariable String email, @RequestBody UpdatePasswordRequest request) {

        // Authenticate user by email before updating password
        boolean authenticated = userService.authenticate(email, request.getOldPassword());
        if (!authenticated) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }

        // Update user password
        userService.updatePasswordByEmail(email, request.getNewPassword());
        return ResponseEntity.ok("Password updated successfully");
    }

    //Put request for changing userdata, will need old password. Created a class for body
    @PutMapping("/{email}")
    public ResponseEntity<String> updateUser(@PathVariable String email, @RequestBody UpdateUserRequest request) {
        // Authenticate user by email before updating
        boolean authenticated = userService.authenticate(email, request.getOldPassword());
        if (!authenticated) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }

        // Update user email and password
        userService.updateUser(email, request.getNewEmail(), request.getNewPassword());
        return ResponseEntity.ok("User updated successfully");
    }

    //Delete request, needs old password, created a class for body
    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email,  @RequestBody DeleteRequest request) {
        // Authenticate user by email before deleting
        boolean authenticated = userService.authenticate(email, request.getPassword());
        if (!authenticated) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }

        // Delete the user
        userService.deleteUserByEmail(email);
        return ResponseEntity.ok("User deleted successfully");
    }

}