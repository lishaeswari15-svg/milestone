package com.example.escrowlite.controller;

import com.example.escrowlite.entity.Release;
import com.example.escrowlite.service.ReleaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/releases")
public class ReleaseController {

    private final ReleaseService releaseService;

    public ReleaseController(ReleaseService releaseService) {
        this.releaseService = releaseService;
    }

    // GET ALL RELEASES
    @GetMapping
    public ResponseEntity<List<Release>> getAllReleases() {
        return ResponseEntity.ok(
                releaseService.getAllReleases()
        );
    }

    // GET RELEASE BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Release> getReleaseById(
            @PathVariable Long id) {

        try {
            return ResponseEntity.ok(
                    releaseService.getReleaseById(id)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // CREATE PAYMENT RELEASE
    @PostMapping
    public ResponseEntity<?> createRelease(
            @RequestParam Long milestoneId) {

        try {

            Release release =
                    releaseService.createRelease(milestoneId);

            return ResponseEntity.ok(release);

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }
}