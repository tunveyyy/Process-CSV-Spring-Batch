package com.hcl.onetest.batch;

import com.hcl.onetest.model.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class Processor implements ItemProcessor<User, User> {

    @Override
    public User process(User user) throws Exception {
        System.out.println("The user " + user.getUsername() + " is " + user.getAge() + " years old and lives in " + user.getCity());
        user.setAge(user.getAge() * 2);
        System.out.println("The user " + user.getUsername() + " has become " + user.getAge() + " years old and lives in " + user.getCity());
        return user;
    }
}
