package org.example.Studentform.Controller;


import org.example.Studentform.Model.Student;
import org.example.Studentform.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @PostMapping("/uploadImage/{id}")
    public ResponseEntity<String> uploadStudentImage(@PathVariable Long id, @RequestParam("image") MultipartFile imageFile) {
        try {
            String imagePath = studentService.saveStudentImage(id, imageFile);
            return ResponseEntity.ok("Image uploaded successfully at path: " + imagePath);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image: " + e.getMessage());
        }
    }


    @GetMapping("/viewImage/{id}")
    public ResponseEntity<byte[]> viewStudentImage(@PathVariable Long id) throws IOException {
        Student student = studentService.getStudentById(Math.toIntExact(id));

        if (student.getImagePath() == null) {
            return ResponseEntity.notFound().build();
        }

        Path imagePath = Paths.get(student.getImagePath());
        byte[] imageBytes = Files.readAllBytes(imagePath);

        // You can dynamically detect content type, or assume one (e.g., image/jpeg)
        String contentType = Files.probeContentType(imagePath);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(imageBytes);
    }


}
