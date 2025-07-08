package org.example.Studentform.Controller;


import org.example.Studentform.Model.Student;
import org.example.Studentform.Model.Teacher;
import org.example.Studentform.Service.PrincipalService;
import org.example.Studentform.Service.StudentService;
import org.example.Studentform.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/headprincipal")
public class PrincipalController {

    @Autowired
    private PrincipalService  principalService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/student/getAll")
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(principalService.getAllStudents());
    }

    @GetMapping("/student/get/{id}")
    public Student getStudentById(@PathVariable int id){
        return principalService.getStudentById(id);
    }

    @GetMapping("/teacher/getAll")
    public ResponseEntity<List<Teacher>> getAllTeachers(){
        return ResponseEntity.ok(principalService.getAllTeachers());
    }

    @GetMapping("/teacher/get/{id}")
    public Teacher getTeacherById(@PathVariable int id){
        return principalService.getTeacherById(id);
    }

    @PostMapping("/teacher/post")
    public Teacher addTeacher(@RequestBody Teacher teacher){
        return principalService.addTeacher(teacher);
    }

    @PostMapping("/student/post")
    @ResponseStatus (HttpStatus.CREATED)
    public Student addStudent(@RequestBody Student student){
        return principalService.addStudent(student);
    }

    @PutMapping("/teacher/update/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable int id, @RequestBody Teacher teacher){
       Teacher updateTeacher = principalService.updateTeacher(id,teacher);
       return ResponseEntity.ok(updateTeacher);
    }

    @PutMapping("/student/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student){
        Student updateStudent = principalService.updateStudent(id,student);
        return ResponseEntity.ok(updateStudent);
    }

    @DeleteMapping("/teacher/delete/{id}")
    public String deleteTeacherById(@PathVariable int id){
       return principalService.deleteTeacherById(id);
    }

    @DeleteMapping("/student/delete/{id}")
    public String deleteStudentById(@PathVariable int id){
        return principalService.deleteStudentById(id);
    }
}

