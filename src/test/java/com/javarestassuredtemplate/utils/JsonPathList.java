package com.javarestassuredtemplate.utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonPathList {
    private Object responseObj;

    public static  Integer getInt(Response response, String path) {
        return (Integer) resolveObj(response, path);
    }

    private static Object resolveObj(Response response, String path){
        JsonPath jsonPath = response.body().jsonPath();
        return jsonPath.getList(path).get(0);
    }

    public static String getString(Response response, String path) {
        return (String) resolveObj(response, path);
    }
}
