package org.example.Studentform.Controller;


import org.example.Studentform.Model.Student;
import org.example.Studentform.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/get/{id}")
    public Student getStudentById(@PathVariable int id){
        return studentService.getStudentById(id);
    }

    @PostMapping("/post")
    @ResponseStatus (HttpStatus.CREATED)
    public Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student){
        Student updateStudent = studentService.updateStudent(id,student);
        return ResponseEntity.ok(updateStudent);
    }

    @GetMapping("/search")
    public ResponseEntity<Student> searchStudent(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String surName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Integer enrollment,
            @RequestParam(required = false) Long phone
    ){
        return ResponseEntity.ok(studentService.findStudent(id,firstName,lastName,surName,enrollment,email,phone));
    }
    }
