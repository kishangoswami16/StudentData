package org.example.Studentform.Model;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;

@Entity
@Data
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String subject;
    private String school;
    private String phone;
    private String email;
}
