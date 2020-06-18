package com.hcl.onetest.controller;

import com.hcl.onetest.model.UploadFileResponse;
import com.hcl.onetest.service.FileStorageService;
import com.hcl.onetest.service.BatchJobService;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LoadController {

    @Autowired
    private BatchJobService batchJobService;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/multi-upload")
    public List<UploadFileResponse> uploadMultipleCSVFile(@RequestParam("files") MultipartFile[] files) {

        for(MultipartFile file: files) {
            fileStorageService.storeFile(file);
        }

        try {
            batchJobService.load();
        } catch (JobParametersInvalidException | JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }


}
