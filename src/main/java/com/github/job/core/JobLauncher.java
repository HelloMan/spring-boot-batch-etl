package com.github.job.core;

public interface JobLauncher {

    void run(JobCommand jobCommand) throws Exception;

    String getJobName();
}
