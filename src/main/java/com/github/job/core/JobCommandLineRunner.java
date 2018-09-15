package com.github.job.core;

import io.airlift.airline.SingleCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JobCommandLineRunner implements CommandLineRunner {

    @Autowired
    private List<JobLauncher> jobLaunchers;

    @Override
    public void run(String... args) throws Exception {
        JobCommand jobLaunchCommand = SingleCommand.singleCommand(JobCommand.class).parse(args);
        Optional<JobLauncher> jobLauncherOptional = jobLaunchers.stream()
                .filter(jobLauncher -> jobLauncher.getJobName().equals(jobLaunchCommand.getJobName()))
                .findFirst();
        if (jobLauncherOptional.isPresent()) {
            log.info("Start to run job with arguments {}" , getArguments(args));
            jobLauncherOptional.get().run(jobLaunchCommand);
        }else {
            log.warn("No job launcher found for job name {}", jobLaunchCommand.getJobName());
        }
    }

    private String getArguments(String[] args) {
        return String.join(" ", args);
    }

}