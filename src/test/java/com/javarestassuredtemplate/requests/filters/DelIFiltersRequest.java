package com.javarestassuredtemplate.requests.filters;

import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class DelIFiltersRequest extends RequestRestBase {
    public DelIFiltersRequest(String filterId){
        requestService = "api/rest/filters/" + filterId ;
        method = Method.DELETE;
    }

}
