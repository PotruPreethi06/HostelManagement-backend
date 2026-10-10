package com.example.hostelmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hostelmanagement.entity.Student;
import com.example.hostelmanagement.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Add student
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get student by ID
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // Update student
    public Student updateStudent(Long id, Student studentDetails) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());
        student.setPassword(studentDetails.getPassword());
        student.setPhone(studentDetails.getPhone());
        student.setCourse(studentDetails.getCourse());
        student.setYear(studentDetails.getYear());

        return studentRepository.save(student);
    }

    // Delete student
    public void deleteStudent(Long id) {

        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }

        studentRepository.deleteById(id);
    }
}