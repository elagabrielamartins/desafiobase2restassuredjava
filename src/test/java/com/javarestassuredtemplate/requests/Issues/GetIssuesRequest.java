package com.javarestassuredtemplate.requests.Issues;

import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class GetIssuesRequest extends RequestRestBase {
    public GetIssuesRequest(){
        requestService = "api/rest/issues";
        method = Method.GET;
    }

}
