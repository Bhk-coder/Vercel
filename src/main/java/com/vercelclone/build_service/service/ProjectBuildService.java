package com.vercelclone.build_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;

@Service
public class ProjectBuildService
{
  @Autowired
  private CommandRunner commandRunner;

  public void buildProject() {
      String targetDir = "/home/app/output";
      Path filepath ;
      try
      {
          File outputdir = new File(targetDir);
          if (!outputdir.exists())
          {
              throw new RuntimeException("Output directory doesn't exist");
          }

          if(new File(targetDir,"pom.xml").exists()) {
              commandRunner.runCommand(targetDir,"mvn", "clean" , "package" , " -DskipTests");
          }
          else if(new File(targetDir,"build.kt").exists()) {
              commandRunner.runCommand(targetDir,"./gradlew","clean","build","-x");
          }
          else
          {
              throw new RuntimeException("No such output directory");
          }
      }

      catch (Exception e)
      {
          throw new RuntimeException("SpringBoot Project Build Failed" + e.getMessage());
      }




  }



}