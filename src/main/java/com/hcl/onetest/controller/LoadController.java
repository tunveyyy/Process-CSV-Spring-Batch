package com.hcl.onetest.controller;

import com.hcl.onetest.model.UploadFileResponse;
import com.hcl.onetest.service.FileStorageService;
import com.hcl.onetest.service.FileUploadService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class LoadController {

    @Autowired
    FileUploadService fileUploadService;

    @PostMapping("/multi-upload")
    public List<UploadFileResponse> uploadMultipleCSVFile(@RequestParam("files") MultipartFile[] files) {

        return Arrays.asList(files)
                .stream()
                .map(file -> fileUploadService.uploadFile(file))
                .collect(Collectors.toList());
    }


}
