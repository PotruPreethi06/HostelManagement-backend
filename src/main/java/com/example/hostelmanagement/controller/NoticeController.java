package com.example.hostelmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hostelmanagement.entity.Notice;
import com.example.hostelmanagement.service.NoticeService;

@RestController
@RequestMapping("/notices")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    // Create Notice
    @PostMapping
    public ResponseEntity<Notice> createNotice(
            @RequestBody Notice notice) {

        Notice savedNotice = noticeService.createNotice(notice);

        return new ResponseEntity<>(savedNotice, HttpStatus.CREATED);
    }

    // Get All Notices
    @GetMapping
    public ResponseEntity<List<Notice>> getAllNotices() {

        return ResponseEntity.ok(
                noticeService.getAllNotices()
        );
    }

    // Get Notice By ID
    @GetMapping("/{id}")
    public ResponseEntity<Notice> getNoticeById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                noticeService.getNoticeById(id)
        );
    }

    // Get Notices By Posted By
    @GetMapping("/postedBy/{postedBy}")
    public ResponseEntity<List<Notice>> getNoticesByPostedBy(
            @PathVariable String postedBy) {

        return ResponseEntity.ok(
                noticeService.getNoticesByPostedBy(postedBy)
        );
    }

    // Update Notice
    @PutMapping("/{id}")
    public ResponseEntity<Notice> updateNotice(
            @PathVariable Long id,
            @RequestBody Notice notice) {

        return ResponseEntity.ok(
                noticeService.updateNotice(id, notice)
        );
    }

    // Delete Notice
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNotice(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                noticeService.deleteNotice(id)
        );
    }
}