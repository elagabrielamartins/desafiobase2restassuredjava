package com.javarestassuredtemplate.steps;

import com.javarestassuredtemplate.requests.user.PostUserRequest;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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

    public static Response cadastrarUserNameRepetido(int nroEmail) {
        //Parâmetros
        String userName = "Usuario Cadastro repetido 1";
        String email = "usuarioautomacao00"+ nroEmail + "@testusuario00" + nroEmail +".com.br";
        return cadastrarUsuario(email, userName );
    }

    public static Response cadastrarEmailRepetido(int nroUser) {
        //Parâmetros
        String userName = "Usuario Cadastro repetido 55" + nroUser;
        String email = "usuarioautomacao551@testusuario551.com.br";
        return cadastrarUsuario(email, userName );
    }

    private static Response cadastrarUsuario(String email, String userName) {
        //Chamadas
        PostUserRequest postUserRequest;

        //Parâmetros
        String password = "123456";
        String nameReal = "Usuario Cadastro repetido";
        String accessLevelName = "reporter";
        String enabled = "true";
        String isProtected = "false";

        //Fluxo
        postUserRequest = new PostUserRequest();
        postUserRequest.setJsonBodyCadastroUsuarioJson(userName, password, nameReal, email, accessLevelName, enabled, isProtected);
        return postUserRequest.executeRequest2();
    }
}
