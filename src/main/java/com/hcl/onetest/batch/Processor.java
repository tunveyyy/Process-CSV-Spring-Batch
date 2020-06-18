package com.hcl.onetest.batch;

import com.hcl.onetest.model.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class Processor implements ItemProcessor<User, User> {
    int i = 1;
    @Override
    public User process(User user) throws Exception {

        user.setAge(user.getAge() * 2);
        return user;
    }
}
