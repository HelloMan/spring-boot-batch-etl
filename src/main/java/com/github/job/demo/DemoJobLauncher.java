package com.github.job.demo;

import com.github.job.core.JobCommand;
import com.github.job.core.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.support.PropertiesConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class DemoJobLauncher implements JobLauncher {

    @Autowired
    private JobOperator jobOperator;


    @Override
    public void run(JobCommand jobCommand) throws Exception {
        Properties jobProperties = jobCommand.toProperties();
        jobOperator.start(jobCommand.getJobName(), PropertiesConverter.propertiesToString(jobProperties));
    }

    @Override
    public String getJobName() {
        return DemoJobConfig.JOB_NAME;
    }
}
