package org.example.Studentform.Controller;


import ch.qos.logback.core.status.Status;
import org.example.Studentform.Model.Student;
import org.example.Studentform.Service.StudentService;
import org.example.Studentform.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/get/all")
    public List<Student> findAll(){
        return teacherService.findAll();
    }
    @GetMapping("/get/{id}")
    public Student getStudentById(@PathVariable int id){
        return teacherService.getStudentById(id);
    }

    @PostMapping("/post")
    @ResponseStatus (HttpStatus.CREATED)
    public Student addStudent(@RequestBody Student student) {
        return teacherService.addStudent(student);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudentById(@PathVariable int id){
      return teacherService.deleteStudentById(id);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student){
        Student updateStudent = teacherService.updateStudent(id,student);
        return ResponseEntity.ok(updateStudent);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<Student> activateStudent(@PathVariable int id) {
        Student updatedStudent = teacherService.activateStudent(id);
        return ResponseEntity.ok(updatedStudent);
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<Student> deactivateStudent(@PathVariable int id) {
        Student updatedStudent = teacherService.deactivateStudent(id);
        return ResponseEntity.ok(updatedStudent);
    }

}
