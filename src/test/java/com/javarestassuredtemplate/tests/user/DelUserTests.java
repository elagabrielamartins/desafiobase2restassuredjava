package com.javarestassuredtemplate.tests.user;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.UserDBSteps;
import com.javarestassuredtemplate.requests.user.DelUserRequest;
import com.javarestassuredtemplate.steps.IncluirUserSteps;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;


public class  DelUserTests  extends TestBase {
    DelUserRequest delUserRequest;
// region Fluxo de Sucesso
    @Test
    public void excluirUsuariocomSucesso() {
        //Incluir Usuario
        Response responseIncluiUsuario = IncluirUserSteps.cadastrarUsuarioComSucessoMinimal();
        int id = responseIncluiUsuario.body().jsonPath().get("user.id");


        //Parâmetros
         int statusCodeEsperado = HttpStatus.SC_NO_CONTENT;

        //Fluxo
        delUserRequest = new DelUserRequest(id);
        Response response = delUserRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado, "Validacao do Campo: status_code");
    }
// endregion

// region Fluxo de exceção
    @Test
    public void apagarUsuarioInexistente(){
        //Parâmetros
        int ID = 999999999;
        int statusCodeEsperado = HttpStatus.SC_NO_CONTENT;

        //Fluxo
        delUserRequest = new DelUserRequest(ID);
        Response response = delUserRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado,"Validacao do Campo: status_code");
    }

    @Test
    public void naoPermitirExcluirUsuarioProtegido() {

        //Incluir Usuario
        Response responseIncluiUsuario = IncluirUserSteps.cadastrarUsuarioProtegido();
        int id = responseIncluiUsuario.body().jsonPath().get("user.id");

        //Parâmetros
        int statusCodeEsperado = HttpStatus.SC_FORBIDDEN;
        String mensagem = "User protected.";
        int codigo = 12;
        String localized = "This account is protected. You are not allowed to access this until the account protection is lifted.";

        //Fluxo
        delUserRequest = new DelUserRequest(id);
        Response response = delUserRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado, "Validacao do Campo: status_code");
        Assert.assertEquals(response.body().jsonPath().get("message").toString(), mensagem, "Validação tentativa deleçao usuario protegido");
        Assert.assertEquals(response.body().jsonPath().get("code").toString(), String.valueOf(codigo), "Validação codigo da menssagem tentativa deleçao usuario protegido");
        Assert.assertEquals(response.body().jsonPath().get("localized").toString(), localized, "Validação detalhes da menssagem tentativa deleçao usuario protegido");

    }
// endregion


    @AfterTest
    public void afterTest() {
        UserDBSteps.deleteUser();
    }
}