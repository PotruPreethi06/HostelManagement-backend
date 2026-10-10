package com.example.hostelmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Fee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    private String month;

    private String paymentDate;

    private String status;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    // Default constructor
    public Fee() {
    }

    // Parameterized constructor
    public Fee(double amount, String month, String paymentDate,
               String status, Student student) {
        this.amount = amount;
        this.month = month;
        this.paymentDate = paymentDate;
        this.status = status;
        this.student = student;
    }

    // Getter and Setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for amount
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Getter and Setter for month
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    // Getter and Setter for paymentDate
    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    // Getter and Setter for status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getter and Setter for student
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}