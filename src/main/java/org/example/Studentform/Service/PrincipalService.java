package org.example.Studentform.Service;

import org.example.Studentform.Model.Student;
import org.example.Studentform.Model.Teacher;
import org.example.Studentform.Repository.StudentRepo;
import org.example.Studentform.Repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PrincipalService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private TeacherRepo teacherRepo;

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepo.findAll();
    }

    public Student getStudentById(int id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Name not found : " + id));
    }

    public Teacher getTeacherById(int id) {
        return teacherRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Name not found : " + id));
    }

    public Student addStudent(Student student) {
        return studentRepo.save(student);
    }

    public Teacher addTeacher(Teacher teacher) {
        return teacherRepo.save(teacher);
    }

    public Teacher updateTeacher(int id, Teacher teacher){
        Teacher existingTeacher = getTeacherById(id);
        existingTeacher.setName(teacher.getName());
        existingTeacher.setSubject(teacher.getSubject());
        existingTeacher.setSchool(teacher.getSchool());
        existingTeacher.setPhone(teacher.getPhone());
        existingTeacher.setEmail(teacher.getEmail());
        return teacherRepo.save(existingTeacher);
    }

    public Student updateStudent(int id, Student student) {
        Student existingStudent = getStudentById(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEnrollment(student.getEnrollment());
        return studentRepo.save(existingStudent);
    }

    public String deleteStudentById(int id){
        if (!studentRepo.existsById(id)) {
            throw new RuntimeException("Student not found : " + id);
        }
        studentRepo.deleteById(id);
        return "Student Record deleted successfully";
    }

    public String deleteTeacherById(int id){
        if (!teacherRepo.existsById(id)) {
            throw new RuntimeException("Teacher not found : " + id);
        }
        teacherRepo.deleteById(id);
        return "Teacher Record deleted successfully";
    }


}
