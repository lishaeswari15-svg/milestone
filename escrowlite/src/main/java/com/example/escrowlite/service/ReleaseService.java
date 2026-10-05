package com.example.escrowlite.service;

import com.example.escrowlite.entity.Milestone;
import com.example.escrowlite.entity.Release;
import com.example.escrowlite.repository.MilestoneRepository;
import com.example.escrowlite.repository.ReleaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReleaseService {

    private final ReleaseRepository releaseRepository;
    private final MilestoneRepository milestoneRepository;

    public ReleaseService(
            ReleaseRepository releaseRepository,
            MilestoneRepository milestoneRepository) {

        this.releaseRepository = releaseRepository;
        this.milestoneRepository = milestoneRepository;
    }

    // CREATE PAYMENT RELEASE
    public Release createRelease(Long milestoneId) {

        Milestone milestone = milestoneRepository.findById(milestoneId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Milestone not found with id: " + milestoneId));

        // Milestone must be approved
        if (!milestone.getClientApproved()) {
            throw new RuntimeException(
                    "Milestone must be approved before payment release");
        }

        Release release = new Release();

        release.setMilestone(milestone);
        release.setAmount(milestone.getAmount());

        return releaseRepository.save(release);
    }

    // GET ALL RELEASES
    public List<Release> getAllReleases() {
        return releaseRepository.findAll();
    }

    // GET RELEASE BY ID
    public Release getReleaseById(Long id) {

        return releaseRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Release not found with id: " + id));
    }
}