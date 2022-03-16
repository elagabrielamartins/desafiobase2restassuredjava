package com.javarestassuredtemplate.steps;

import com.javarestassuredtemplate.requests.user.PostUserRequest;
import io.restassured.response.Response;


public class IncluirUserSteps {
    public static Response cadastrarUsuarioComSucessoMinimal() {
        //Chamadas
        PostUserRequest postUserRequest;

        //Parâmetros
        String userName = "Usuario para Minimal";

        //Fluxo
        postUserRequest = new PostUserRequest();
        postUserRequest.setJsonBodyCadastroUsuarioMinimal(userName);
        return postUserRequest.executeRequest2();
    }

    public static Response cadastrarUsuarioProtegido() {
        //Chamadas
        PostUserRequest postUserRequest;

        //Parâmetros
        String userName = "Usuario para protegido";
        String password = "123456";
        String nameReal = "Usuario para protegido";
        String email = "usuarioautomacaoProtegido@testusuarioProtegido.com.br";
        String accessLevelName = "updater";
        String enabled = "true";
        String isProtected = "true";

        //Fluxo
        postUserRequest = new PostUserRequest();
        postUserRequest.setJsonBodyCadastroUsuarioJson(userName, password, nameReal, email, accessLevelName, enabled, isProtected);
        return postUserRequest.executeRequest2();
    }
}
