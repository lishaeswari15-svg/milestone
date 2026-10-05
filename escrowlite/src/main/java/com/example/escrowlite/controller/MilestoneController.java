package com.example.escrowlite.controller;

import com.example.escrowlite.entity.Milestone;
import com.example.escrowlite.service.MilestoneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/milestones")
public class MilestoneController {

    private final MilestoneService milestoneService;

    public MilestoneController(MilestoneService milestoneService) {
        this.milestoneService = milestoneService;
    }

    // GET all milestones
    @GetMapping
    public List<Milestone> getAllMilestones() {
        return milestoneService.getAllMilestones();
    }

    // GET milestone by ID
    @GetMapping("/{id}")
    public Milestone getMilestoneById(@PathVariable Long id) {
        return milestoneService.getMilestoneById(id);
    }

    // POST - create milestone for a project
    @PostMapping
    public ResponseEntity<Milestone> createMilestone(
            @RequestParam Long projectId,
            @RequestBody Milestone milestone) {

        Milestone createdMilestone =
                milestoneService.createMilestone(projectId, milestone);

        return ResponseEntity.ok(createdMilestone);
    }

    // PUT - update milestone
    @PutMapping("/{id}")
    public Milestone updateMilestone(
            @PathVariable Long id,
            @RequestBody Milestone milestone) {

        return milestoneService.updateMilestone(id, milestone);
    }

    // MARK MILESTONE AS DELIVERED
    @PutMapping("/{id}/deliver")
    public Milestone markAsDelivered(@PathVariable Long id) {

        return milestoneService.markAsDelivered(id);
    }

    // CLIENT APPROVES MILESTONE
    @PutMapping("/{id}/approve")
    public Milestone approveMilestone(@PathVariable Long id) {

        return milestoneService.approveMilestone(id);
    }

    // DELETE milestone
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMilestone(
            @PathVariable Long id) {

        milestoneService.deleteMilestone(id);

        return ResponseEntity.noContent().build();
    }
}
