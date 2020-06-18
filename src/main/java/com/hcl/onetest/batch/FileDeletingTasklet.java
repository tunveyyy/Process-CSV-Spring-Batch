package com.hcl.onetest.batch;

import java.io.File;
import java.io.IOException;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

public class FileDeletingTasklet implements Tasklet, InitializingBean {

    private Resource[] resources;

    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)  {
        System.out.println("The length of files for deleting is " + resources.length);
        for(Resource r: resources) {
            File file = null;
            try {
                file = r.getFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            boolean deleted = file.delete();
            System.out.println("The file with name " + file.getName() + " has delete status as " + deleted);
            /*if (!deleted) {
                throw new UnexpectedJobExecutionException("Could not delete file " + file.getPath());
            }*/

        }

        return RepeatStatus.FINISHED;
    }

    public void setResources(Resource[] resources) {
        this.resources = resources;
    }

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(resources, "Directory must be set");
    }
}