package com.example.hostelmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.hostelmanagement.entity.Student;
import com.example.hostelmanagement.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Add Student
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {

        Student savedStudent = studentService.addStudent(student);

        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    // Get All Students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {

        List<Student> students = studentService.getAllStudents();

        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // Get Student By ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {

        Optional<Student> student = studentService.getStudentById(id);

        if (student.isPresent()) {
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Update Student
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @RequestBody Student student) {

        Student updatedStudent = studentService.updateStudent(id, student);

        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    // Delete Student
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return new ResponseEntity<>(
                "Student deleted successfully",
                HttpStatus.OK
        );
    }
}