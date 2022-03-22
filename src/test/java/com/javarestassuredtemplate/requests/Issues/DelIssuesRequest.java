package com.javarestassuredtemplate.requests.Issues;

import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class DelIssuesRequest  extends RequestRestBase {
    public DelIssuesRequest(int idBug){
        requestService = "api/rest/issues/" + idBug;
        method = Method.DELETE;
    }

}
