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

import com.example.hostelmanagement.entity.HostelUpdate;
import com.example.hostelmanagement.service.HostelUpdateService;

@RestController
@RequestMapping("/hostel-updates")
public class HostelUpdateController {

    @Autowired
    private HostelUpdateService hostelUpdateService;

    // =========================
    // CREATE HOSTEL UPDATE
    // =========================

    @PostMapping
    public ResponseEntity<HostelUpdate> createUpdate(
            @RequestBody HostelUpdate update) {

        HostelUpdate savedUpdate =
                hostelUpdateService.createUpdate(update);

        return new ResponseEntity<>(
                savedUpdate,
                HttpStatus.CREATED
        );
    }

    // =========================
    // GET ALL HOSTEL UPDATES
    // =========================

    @GetMapping
    public ResponseEntity<List<HostelUpdate>> getAllUpdates() {

        return ResponseEntity.ok(
                hostelUpdateService.getAllUpdates()
        );
    }

    // =========================
    // GET UPDATE BY ID
    // =========================

    @GetMapping("/{id}")
    public ResponseEntity<HostelUpdate> getUpdateById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                hostelUpdateService.getUpdateById(id)
        );
    }

    // =========================
    // GET UPDATES BY CATEGORY
    // =========================

    @GetMapping("/category/{category}")
    public ResponseEntity<List<HostelUpdate>>
    getUpdatesByCategory(
            @PathVariable String category) {

        return ResponseEntity.ok(
                hostelUpdateService
                        .getUpdatesByCategory(category)
        );
    }

    // =========================
    // GET UPDATES BY STATUS
    // =========================

    @GetMapping("/status/{status}")
    public ResponseEntity<List<HostelUpdate>>
    getUpdatesByStatus(
            @PathVariable String status) {

        return ResponseEntity.ok(
                hostelUpdateService
                        .getUpdatesByStatus(status)
        );
    }

    // =========================
    // UPDATE HOSTEL UPDATE
    // =========================

    @PutMapping("/{id}")
    public ResponseEntity<HostelUpdate> updateUpdate(
            @PathVariable Long id,
            @RequestBody HostelUpdate update) {

        return ResponseEntity.ok(
                hostelUpdateService
                        .updateUpdate(id, update)
        );
    }

    // =========================
    // DELETE HOSTEL UPDATE
    // =========================

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUpdate(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                hostelUpdateService.deleteUpdate(id)
        );
    }
}