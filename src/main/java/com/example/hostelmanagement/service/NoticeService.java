package com.example.hostelmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hostelmanagement.entity.Notice;
import com.example.hostelmanagement.repository.NoticeRepository;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    // Create Notice
    public Notice createNotice(Notice notice) {
        return noticeRepository.save(notice);
    }

    // Get All Notices
    public List<Notice> getAllNotices() {
        return noticeRepository.findAll();
    }

    // Get Notice By ID
    public Notice getNoticeById(Long id) {
        return noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notice not found"));
    }

    // Get Notices By Posted By
    public List<Notice> getNoticesByPostedBy(String postedBy) {
        return noticeRepository.findByPostedBy(postedBy);
    }

    // Update Notice
    public Notice updateNotice(Long id, Notice updatedNotice) {

        Notice existingNotice = noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notice not found"));

        existingNotice.setTitle(updatedNotice.getTitle());
        existingNotice.setMessage(updatedNotice.getMessage());
        existingNotice.setNoticeDate(updatedNotice.getNoticeDate());
        existingNotice.setPostedBy(updatedNotice.getPostedBy());

        return noticeRepository.save(existingNotice);
    }

    // Delete Notice
    public String deleteNotice(Long id) {

        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notice not found"));

        noticeRepository.delete(notice);

        return "Notice deleted successfully";
    }
}