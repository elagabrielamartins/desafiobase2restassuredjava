package com.javarestassuredtemplate.requests.filters;

import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class GetFiltersIDRequest extends RequestRestBase {
    public GetFiltersIDRequest(Integer filterId){
        requestService = "api/rest/filters/"+ filterId;
        method = Method.GET;
    }

}
