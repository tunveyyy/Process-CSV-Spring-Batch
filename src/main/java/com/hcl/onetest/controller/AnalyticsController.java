package com.hcl.onetest.controller;

import com.hcl.onetest.exception.NoResourceException;
import com.hcl.onetest.model.User;
import com.hcl.onetest.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/users")
    public ResponseEntity<Object> getUsers() throws Exception {
        List<User> users = analyticsService.fetchUsers();

        System.out.println(users.isEmpty());
        if(users.isEmpty())
            return new ResponseEntity<>(new NoResourceException("No users"), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(users,HttpStatus.OK);
    }



    @GetMapping("/execution")
    public NoResourceException getBatchProcessingReport() {
        return new NoResourceException("Batch process not workiing");
    }
}
