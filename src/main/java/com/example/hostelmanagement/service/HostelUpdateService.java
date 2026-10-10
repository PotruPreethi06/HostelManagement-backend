package com.example.hostelmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hostelmanagement.entity.HostelUpdate;
import com.example.hostelmanagement.repository.HostelUpdateRepository;

@Service
public class HostelUpdateService {

    @Autowired
    private HostelUpdateRepository hostelUpdateRepository;

    // =========================
    // CREATE HOSTEL UPDATE
    // =========================

    public HostelUpdate createUpdate(HostelUpdate update) {

        if (update.getStatus() == null ||
            update.getStatus().isEmpty()) {

            update.setStatus("ACTIVE");
        }

        return hostelUpdateRepository.save(update);
    }

    // =========================
    // GET ALL UPDATES
    // =========================

    public List<HostelUpdate> getAllUpdates() {

        return hostelUpdateRepository.findAll();
    }

    // =========================
    // GET UPDATE BY ID
    // =========================

    public HostelUpdate getUpdateById(Long id) {

        return hostelUpdateRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Hostel update not found"));
    }

    // =========================
    // GET UPDATES BY CATEGORY
    // =========================

    public List<HostelUpdate> getUpdatesByCategory(
            String category) {

        return hostelUpdateRepository
                .findByCategory(category);
    }

    // =========================
    // GET UPDATES BY STATUS
    // =========================

    public List<HostelUpdate> getUpdatesByStatus(
            String status) {

        return hostelUpdateRepository
                .findByStatus(status);
    }

    // =========================
    // UPDATE HOSTEL UPDATE
    // =========================

    public HostelUpdate updateUpdate(
            Long id,
            HostelUpdate updatedUpdate) {

        HostelUpdate existingUpdate =
                hostelUpdateRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Hostel update not found"));

        existingUpdate.setTitle(
                updatedUpdate.getTitle()
        );

        existingUpdate.setDescription(
                updatedUpdate.getDescription()
        );

        existingUpdate.setUpdateDate(
                updatedUpdate.getUpdateDate()
        );

        existingUpdate.setCategory(
                updatedUpdate.getCategory()
        );

        if (updatedUpdate.getStatus() != null &&
            !updatedUpdate.getStatus().isEmpty()) {

            existingUpdate.setStatus(
                    updatedUpdate.getStatus()
            );
        }

        return hostelUpdateRepository.save(existingUpdate);
    }

    // =========================
    // DELETE HOSTEL UPDATE
    // =========================

    public String deleteUpdate(Long id) {

        HostelUpdate update =
                hostelUpdateRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Hostel update not found"));

        hostelUpdateRepository.delete(update);

        return "Hostel update deleted successfully";
    }
}