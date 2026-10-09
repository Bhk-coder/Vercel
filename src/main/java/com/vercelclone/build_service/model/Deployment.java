package com.vercelclone.build_service.model;

import java.time.LocalDateTime;


public class Deployment
{
  private int deployment_id;

  private int project_id;

  private String repo_url;
  private String  commitSha;
  private int workerId;

  private LocalDateTime createdAt;

  private LocalDateTime startedAt;

  private LocalDateTime lastheartbeat;

  private DeploymentStatus status;

  private String artifactPath;

  private String failureReason;
  private int retrycount ;


}
