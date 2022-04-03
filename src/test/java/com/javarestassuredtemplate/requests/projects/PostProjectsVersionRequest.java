package com.javarestassuredtemplate.requests.projects;

import com.javarestassuredtemplate.utils.GeneralUtils;

public class PostProjectsVersionRequest extends PostProjectsRequest {
    public PostProjectsVersionRequest(int id) {
        super();
        requestService = requestService + id + "/versions/";
    }

    public void setJsonBodyUsingJsonFile(
            String projectVersion,
            String projectDescription,
            String projectRelease,
            String projectObsolete,
            String projectDate) {
        jsonBody = GeneralUtils.readFileToAString("src/test/java/com/javarestassuredtemplate/jsons/projects/PostProjectsVersion.json").
                replace("$projectVersion", projectVersion).
                replace("$projectDescription", (projectDescription)).
                replace("$projectRelease", projectRelease).
                replace("$projectObsolete", projectObsolete).
                replace("$projectDate", String.valueOf(projectDate));
    }
}