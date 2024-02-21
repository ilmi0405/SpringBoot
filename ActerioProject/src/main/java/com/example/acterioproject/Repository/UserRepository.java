package com.example.acterioproject.Repository;

import com.example.acterioproject.Pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    boolean existsByEmail(String email);

    //Query for retrieving emails
    @Query("SELECT u.email FROM User u")
    List<String> findAllEmails();
}