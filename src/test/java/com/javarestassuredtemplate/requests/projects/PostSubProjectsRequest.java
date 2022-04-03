package com.javarestassuredtemplate.requests.projects;

import com.javarestassuredtemplate.utils.GeneralUtils;

public class PostSubProjectsRequest extends PostProjectsRequest {
    public PostSubProjectsRequest(int ID) {
        super();
        requestService = requestService + ID + "/subprojects";
    }

    public void setJsonBodyUsingJsonFile(
            String subProjectName,
            String inherit) {
        jsonBody = GeneralUtils.readFileToAString("src/test/java/com/javarestassuredtemplate/jsons/projects/PostSubProjects.json").
                replace("$subProjectName", subProjectName).
                replace("$inherit", inherit);
    }
}
