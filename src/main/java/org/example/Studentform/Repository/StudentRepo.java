package org.example.Studentform.Repository;

import org.example.Studentform.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepo extends JpaRepository<Student,Integer> {

}
