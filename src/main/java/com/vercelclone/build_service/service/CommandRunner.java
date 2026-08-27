package com.vercelclone.build_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class CommandRunner {

  private static final Logger log = LoggerFactory.getLogger(CommandRunner.class);

  public void runCommand(String dirPath, String... command) throws InterruptedException, IOException {
    ProcessBuilder pb = new ProcessBuilder(command);

    pb.directory(new File(dirPath));

    pb.inheritIO();

    log.info("Executing command: " + String.join(" ", command), dirPath);
    Process process = pb.start();

    boolean finished = process.waitFor(30, TimeUnit.SECONDS);

    if(!finished)
    {
      process.destroyForcibly();
      throw new RuntimeException("Command execution timed out");
    }

    int exitcode = process.exitValue();
    if(exitcode != 0)
    {
      log.error("Command execution timed out");
      throw new RuntimeException("Command execution timed out");
    }

    log.info("Command execution completed");

  }
}