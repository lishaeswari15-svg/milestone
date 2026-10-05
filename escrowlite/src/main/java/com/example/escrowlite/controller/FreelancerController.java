package com.example.escrowlite.controller;

import com.example.escrowlite.entity.Freelancer;
import com.example.escrowlite.service.FreelancerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/freelancers")
public class FreelancerController {

    private final FreelancerService freelancerService;

    public FreelancerController(FreelancerService freelancerService) {
        this.freelancerService = freelancerService;
    }

    @GetMapping
    public List<Freelancer> getAllFreelancers() {
        return freelancerService.getAllFreelancers();
    }

    @GetMapping("/{id}")
    public Freelancer getFreelancerById(@PathVariable Long id) {
        return freelancerService.getFreelancerById(id);
    }

    @PostMapping
    public Freelancer createFreelancer(@RequestBody Freelancer freelancer) {
        return freelancerService.saveFreelancer(freelancer);
    }

    @PutMapping("/{id}")
    public Freelancer updateFreelancer(
            @PathVariable Long id,
            @RequestBody Freelancer freelancer) {

        Freelancer existingFreelancer =
                freelancerService.getFreelancerById(id);

        if (existingFreelancer == null) {
            return null;
        }

        existingFreelancer.setName(freelancer.getName());
        existingFreelancer.setEmail(freelancer.getEmail());
        existingFreelancer.setSkill(freelancer.getSkill());

        return freelancerService.saveFreelancer(existingFreelancer);
    }

    @DeleteMapping("/{id}")
    public String deleteFreelancer(@PathVariable Long id) {

        Freelancer existingFreelancer =
                freelancerService.getFreelancerById(id);

        if (existingFreelancer == null) {
            return "Freelancer not found";
        }

        freelancerService.deleteFreelancer(id);

        return "Freelancer deleted successfully";
    }
}