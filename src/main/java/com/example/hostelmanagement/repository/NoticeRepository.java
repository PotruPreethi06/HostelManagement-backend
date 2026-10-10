package com.example.hostelmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hostelmanagement.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findByPostedBy(String postedBy);
}