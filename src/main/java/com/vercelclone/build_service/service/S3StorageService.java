package com.vercelclone.build_service.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class S3StorageService
{

  private final S3Client s3Client  ;
  private final String   bucketName = "";

  public void uploadDirectoryContent(Path distPath , String prefix) throws IOException {
    try (Stream<Path> paths = Files.walk(distPath)) {
         paths.filter(Files ::isRegularFile)
                 .forEach(file -> {
                    Path relativePath = distPath.relativize(file);
                    String objectKey = prefix + relativePath.toString();
                    uploadS3(file);
                 });   ;
      // We have a stream of all files and folders!
    }
  }



  public void uploadS3(Path file)
  {
    String s3Key = "";

    PutObjectRequest putObjectRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(s3Key)
            .build();
   s3Client.putObject(putObjectRequest, RequestBody.fromFile(file));
    System.out.println("Downloaded" + s3Key + " from S3");
  }


}
