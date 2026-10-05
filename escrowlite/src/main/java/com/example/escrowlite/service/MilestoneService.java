package com.example.escrowlite.service;

import com.example.escrowlite.entity.Milestone;
import com.example.escrowlite.entity.Project;
import com.example.escrowlite.repository.MilestoneRepository;
import com.example.escrowlite.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;
    private final ProjectRepository projectRepository;

    public MilestoneService(
            MilestoneRepository milestoneRepository,
            ProjectRepository projectRepository) {

        this.milestoneRepository = milestoneRepository;
        this.projectRepository = projectRepository;
    }

    // GET ALL MILESTONES
    public List<Milestone> getAllMilestones() {
        return milestoneRepository.findAll();
    }

    // GET MILESTONE BY ID
    public Milestone getMilestoneById(Long id) {

        return milestoneRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Milestone not found with id: " + id));
    }

    // CREATE MILESTONE FOR PROJECT
    public Milestone createMilestone(
            Long projectId,
            Milestone milestone) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Project not found with id: " + projectId));

        milestone.setProject(project);

        return milestoneRepository.save(milestone);
    }

    // UPDATE MILESTONE
    public Milestone updateMilestone(
            Long id,
            Milestone updatedMilestone) {

        Milestone existingMilestone =
                milestoneRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Milestone not found with id: " + id));

        existingMilestone.setTitle(
                updatedMilestone.getTitle());

        existingMilestone.setAmount(
                updatedMilestone.getAmount());

        existingMilestone.setDelivered(
                updatedMilestone.getDelivered());

        existingMilestone.setClientApproved(
                updatedMilestone.getClientApproved());

        existingMilestone.setStatus(
                updatedMilestone.getStatus());

        return milestoneRepository.save(existingMilestone);
    }

    // MARK MILESTONE AS DELIVERED
    public Milestone markAsDelivered(Long id) {

        Milestone milestone = milestoneRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Milestone not found with id: " + id));

        if (milestone.getDelivered()) {
            throw new RuntimeException(
                    "Milestone is already delivered");
        }

        milestone.setDelivered(true);
        milestone.setStatus("DELIVERED");

        return milestoneRepository.save(milestone);
    }

    // CLIENT APPROVES MILESTONE
    public Milestone approveMilestone(Long id) {

        Milestone milestone = milestoneRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Milestone not found with id: " + id));

        if (!milestone.getDelivered()) {
            throw new RuntimeException(
                    "Milestone must be delivered before approval");
        }

        if (milestone.getClientApproved()) {
            throw new RuntimeException(
                    "Milestone is already approved");
        }

        milestone.setClientApproved(true);
        milestone.setStatus("APPROVED");

        return milestoneRepository.save(milestone);
    }

    // DELETE MILESTONE
    public void deleteMilestone(Long id) {

        if (!milestoneRepository.existsById(id)) {
            throw new RuntimeException(
                    "Milestone not found with id: " + id);
        }

        milestoneRepository.deleteById(id);
    }
}