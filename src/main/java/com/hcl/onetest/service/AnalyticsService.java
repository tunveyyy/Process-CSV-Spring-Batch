package com.hcl.onetest.service;

import com.hcl.onetest.model.User;
import com.hcl.onetest.repository.UserRepository;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    public List<User> fetchUsers() {
        return userRepository.findAll();
    }

    public void fetchExecutionReport(Long jobId) {
    }

}
