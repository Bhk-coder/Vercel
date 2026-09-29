package com.vercelclone.build_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;

@Service
@RequiredArgsConstructor
public class OrchestratorService
{
  @Autowired
  private CommandRunner commandRunner;

  private final S3Client s3Client;
  public void orchestratorService()
  {






  }



}