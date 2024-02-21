package com.example.acterioproject.Pojo;


import jakarta.persistence.*;
import lombok.*;


@Entity
//lombok to remove setter, getter and default constructor
@Getter @Setter @NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String email;

    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }


}
