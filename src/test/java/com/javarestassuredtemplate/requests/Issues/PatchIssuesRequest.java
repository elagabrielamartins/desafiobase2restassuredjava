package com.javarestassuredtemplate.requests.Issues;

import com.javarestassuredtemplate.bases.RequestRestBase;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.http.Method;

public class PatchIssuesRequest extends RequestRestBase {
    public PatchIssuesRequest(int idBug){
        requestService = "api/rest/issues/" + idBug;
        method = Method.PATCH;
    }

    public void setJsonBodyUsingJsonFile(
            String status_name){
        jsonBody = GeneralUtils.readFileToAString("src/test/java/com/javarestassuredtemplate/jsons/issues/PatchIssues.json").
                replace("$status_name", status_name);
    }
    public void setJsonBodyUpdatePriority(
            String prioridade,
            String status_name){
        jsonBody = GeneralUtils.readFileToAString("src/test/java/com/javarestassuredtemplate/jsons/issues/PatchIssuesPriority.json").
                replace("$priority", prioridade)
                .replace("$status_name", status_name);
    }
}