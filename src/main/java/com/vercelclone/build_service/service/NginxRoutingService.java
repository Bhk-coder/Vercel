package com.vercelclone.build_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class NginxRoutingService
{
    private final CommandRunner commandRunner;
    private final String template = """
            server{
            
            listen 80;
            serve_name [PROJECT_ID].vercelplatfom.com;
            
            location /  {
             proxy_pass  https://my-platform-bucket.s3.amazonaws.com/[PROJECT_ID]/;
            }
            
            
            }
            
            
            
            """;

    public void configureRoute(String project_id) throws Exception
    {
        String finalconfig = template.replace("[PROJECT_ID]", project_id);

        Files.writeString(Path.of("project_id  " + ".conf") ,  finalconfig);

        commandRunner.runCommand(" docker restart nginx-container");
    }
}
