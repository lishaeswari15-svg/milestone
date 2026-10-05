package com.example.escrowlite.service;

import com.example.escrowlite.entity.Freelancer;
import com.example.escrowlite.repository.FreelancerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FreelancerService {

    private final FreelancerRepository freelancerRepository;

    public FreelancerService(FreelancerRepository freelancerRepository) {
        this.freelancerRepository = freelancerRepository;
    }

    public List<Freelancer> getAllFreelancers() {
        return freelancerRepository.findAll();
    }

    public Freelancer getFreelancerById(Long id) {
        return freelancerRepository.findById(id).orElse(null);
    }

    public Freelancer saveFreelancer(Freelancer freelancer) {
        return freelancerRepository.save(freelancer);
    }

    public void deleteFreelancer(Long id) {
        freelancerRepository.deleteById(id);
    }
}