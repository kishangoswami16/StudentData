package org.example.Studentform.Model;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Table(name="Student")

@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String firstName;
    private String lastName;
    private String surName;
    private Integer enrollment;
    private Long phone;
    private String email;

    private String imagePath;
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;
}
