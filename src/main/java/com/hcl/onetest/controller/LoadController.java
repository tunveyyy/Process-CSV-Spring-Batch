package com.hcl.onetest.controller;

import com.hcl.onetest.model.UploadFileResponse;
import com.hcl.onetest.service.FileStorageService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/load")
public class LoadController {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping
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


    @PostMapping("/multi-upload")
    public List<UploadFileResponse> uploadMultipleCSVFile(@RequestParam("files") MultipartFile[] files) {
        System.out.println("++++++++++++++++++++++++++++++++++++");
        System.out.println(files.length);
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @PostMapping("/file-upload")
    public UploadFileResponse uploadFile(@RequestParam("file")MultipartFile file) {
        String fileName =  fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());

    }
}
