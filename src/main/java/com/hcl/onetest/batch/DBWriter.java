package com.hcl.onetest.batch;

import com.hcl.onetest.model.User;
import com.hcl.onetest.repository.UserRepository;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class DBWriter implements ItemWriter<User> {

    @Autowired
    private UserRepository userRepository;

    private Long jobID;
    private Date date;

    @Override
    public void write(List<? extends User> users) throws Exception {
        for(User user : users) {
            user.setJobId(jobID);
            userRepository.save(user);
        }
    }

    @BeforeStep
    public void getInterStepDate(StepExecution stepExecution) {
        JobExecution jobExecution = stepExecution.getJobExecution();
        this.jobID = jobExecution.getJobId();
        this.date = jobExecution.getEndTime();
    }
}
