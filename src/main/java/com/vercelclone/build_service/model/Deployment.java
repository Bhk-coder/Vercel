package com.vercelclone.build_service.model;

public class Deployment
{
  private int deployment_id;

  private int project_id;

  private String repo_url;
  private int commitSha;

  private int workerId;

  private int createdAt;

  private int startedAt;

  private int finishedAt;

  private int lastheartbeat;

  private String status;
}
