package com.hcl.onetest.service;

import com.hcl.onetest.model.UploadFileResponse;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
public class FileUploadService {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {

        String fileName =  fileStorageService.storeFile(file);

        try {
            load();

        } catch (JobParametersInvalidException | JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        }

        return new UploadFileResponse(fileName, file.getContentType(), file.getSize());

    }


    public BatchStatus load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        System.out.println("Job Execution " + jobExecution.getStatus());
        System.out.println("Batch is running");
        while(jobExecution.isRunning()) {
            System.out.println("...");
        }

        return jobExecution.getStatus();
    }

}
