package org.example.Studentform.Service;


import org.example.Studentform.Model.Student;
import org.example.Studentform.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;


    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }

    public Student getStudentById(int id){
        return studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Name not found : " + id));
    }

    public Student addStudent(Student student){
        return studentRepo.save(student);
    }

        public Student findStudent(Long id,String firstName,String lastName,String surName,Integer enrollment,String email,Long phone) {
        List<Student> student = studentRepo.findAll();

        return student.stream()
                .filter(std -> (id == null || std.getId().equals(id)) &&
                               (firstName == null || std.getFirstName().equals(firstName)) &&
                               (lastName == null || std.getLastName().equals(lastName)) &&
                               (surName == null || std.getSurName().equals(surName)) &&
                               (enrollment == null || std.getEnrollment().equals(enrollment)) &&
                               (email == null || Objects.equals(std.getEmail(), email)) &&
                               (phone == null || Objects.equals(std.getPhone(), phone)))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No matching employee found with given criteria."));
    }

    public Student updateStudent(int id, Student student){
        Student existingStudent =  getStudentById(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEnrollment(student.getEnrollment());
        return studentRepo.save(existingStudent);
    }
}