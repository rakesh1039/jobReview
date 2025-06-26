package com.job.jobApp.job.impl;

import com.job.jobApp.job.Job;
import com.job.jobApp.job.JobRepository;
import com.job.jobApp.job.JobService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        this.jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJob(Long id) {
        try {
            if(jobRepository.findById(id).isPresent()) {
                jobRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Job updatejob, Long id) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatejob.getTitle());
            job.setDescription(updatejob.getDescription());
            job.setMinimumSalry(updatejob.getMinimumSalry());
            job.setMaximumSalary(updatejob.getMaximumSalary());
            job.setLocation(updatejob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
