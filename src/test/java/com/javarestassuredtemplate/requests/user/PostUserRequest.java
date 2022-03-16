package com.javarestassuredtemplate.requests.user;



import com.javarestassuredtemplate.bases.RequestRestBase;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.http.Method;

public class PostUserRequest extends RequestRestBase {
    public PostUserRequest(){
        requestService = "api/rest/users/";
        method = Method.POST;
    }

    public void setJsonBodyCadastroUsuarioJson(
            String userName,
            String password,
            String nameReal,
            String email,
            String accessLevelName,
            String enabled,
            String isProtected){
        jsonBody = GeneralUtils.readFileToAString("src/test/java/com/javarestassuredtemplate/jsons/user/postCadastroUsuario.json").
                replace("$userName", userName).
                replace("$password", password).
                replace("$nameReal", nameReal ).
                replace("$email", email ).
                replace("$accessLevelName", accessLevelName).
                replace("$enabled", enabled).
                replace("$isProtected", isProtected);
    }

    public void setJsonBodyCadastroUsuarioMinimal(
            String userName
           ){
        jsonBody = GeneralUtils.readFileToAString("src/test/java/com/javarestassuredtemplate/jsons/user/postCadastroUsuarioMinimal.json").
                replace("$userName", userName);
    }

}

