package com.example.hostelmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hostelmanagement.entity.HostelUpdate;

public interface HostelUpdateRepository
        extends JpaRepository<HostelUpdate, Long> {

    List<HostelUpdate> findByCategory(String category);

    List<HostelUpdate> findByStatus(String status);
}