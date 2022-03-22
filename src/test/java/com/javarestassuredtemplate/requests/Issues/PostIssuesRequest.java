package com.javarestassuredtemplate.requests.Issues;

import com.javarestassuredtemplate.bases.RequestRestBase;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.http.Method;

public class PostIssuesRequest extends RequestRestBase {
    public PostIssuesRequest(){
        requestService = "api/rest/issues/";
        method = Method.POST;
    }

    public void setJsonBodyUsingJsonFile(
            String summary,
            String description,
            String category_name,
            String project_name){
        jsonBody = GeneralUtils.readFileToAString("src/test/java/com/javarestassuredtemplate/jsons/issues/PostIssues.json").
                replace("$summary", summary).
                replace("$description", description ).
                replace("$category_name", category_name ).
                replace("$project_name", project_name);
    }

}
