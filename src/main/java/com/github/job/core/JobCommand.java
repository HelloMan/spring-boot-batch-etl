package com.github.job.core;

import io.airlift.airline.Arguments;
import io.airlift.airline.Command;
import io.airlift.airline.Option;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Properties;

@Command(name = "jobLaunch")
@Slf4j
@Data
public class JobCommand {

    @Option(name = {"-j", "--job"}, required = true)
    private String jobName;

    @Arguments
    private List<String> arguments;

    public JobCommand(){}

    public Properties toProperties() {

        final Properties p = new Properties();
        //below two lines only used for test

        if (arguments != null) {
            arguments.forEach(s -> {
                String[] argument = s.split("=");
                if (argument.length == 2 && !StringUtils.isEmpty(argument[0]) && !StringUtils.isEmpty(argument[1])) {
                    p.setProperty(argument[0], argument[1]);
                }else {
                    log.warn("arguments {} is invalid, the arguments should appear like x=y",s);
                }
            });
        }
        return p;
    }
}
