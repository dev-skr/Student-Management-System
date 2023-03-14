package com.example.school.controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.school.service.StudentH2Service;
import com.example.school.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
@RestController
public class StudentController {

@Autowired
 private StudentH2Service studentService;

    @GetMapping("/students")
    public ArrayList<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/students/{studentId}")
    public Student getById(@PathVariable("studentId") int studentId) {
        return studentService.getStudentById(studentId);
    }

    @PostMapping("/students")
    public Student postStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PostMapping("/students/bulk")
    public String postStudents(@RequestBody ArrayList<Student> students) {
        return studentService.addStudents(students);
    }

    @PutMapping("/students/{studentId}")
    public Student putStudent(@PathVariable("studentId") int studentId, @RequestBody Student student) {
        return studentService.updateStudent(studentId,student);
    }

    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable("studentId") int studentId) {
       studentService.deleteStudent(studentId);
    }

}

