package com.javarestassuredtemplate.tests.projects;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.projects.PostSubProjectsRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PostSubProjectsTests extends TestBase {
    PostSubProjectsRequest postSubProjectsRequest;
    SoftAssert softAssert;

    @Test
    public void cadastrarSubProjetocomSucesso(){
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        int ID = 6;
        String subProjectName = "Projeto 1 Criado para os testes Automação sub";
        String inherit = "true";

        int statusCodeEsperado = HttpStatus.SC_NO_CONTENT;

        //Fluxo
        postSubProjectsRequest = new PostSubProjectsRequest(ID);
        postSubProjectsRequest.setJsonBodyUsingJsonFile(subProjectName, inherit);
        Response response =postSubProjectsRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
    }

    @Test
    public void CadastrarSubProjetojaCadastrado(){
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        int ID = 1;
        String subProjectName = "Projeto 1 Criado para os testes Automação sub";
        String inherit = "1";

        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;
        String menssage ="Project '3' is already a subproject of '1'";
        String code = "704";

        //Fluxo
        postSubProjectsRequest = new PostSubProjectsRequest(ID);
        postSubProjectsRequest.setJsonBodyUsingJsonFile(subProjectName, inherit);
        Response response =postSubProjectsRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("message").toString(), menssage, "Validação campo: messagee");
        softAssert.assertEquals(response.body().jsonPath().get("code").toString(), code, "Validação campo: code");
        softAssert.assertAll();
    }

    //Fluxo exceção
    @Test
    public void CadastrarSubProjetojaCadastradoIdInexistente(){
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        int ID = 1;
        String subProjectName = "Projeto 1 Criado para os testes Automação sub";
        String inherit = "1";

        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;
        String menssage = "Project '"+inherit+"' not found";
        String code = "700";

        //Fluxo
        postSubProjectsRequest = new PostSubProjectsRequest(ID);
        postSubProjectsRequest.setJsonBodyUsingJsonFile(subProjectName, inherit);
        Response response =postSubProjectsRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("message").toString(), menssage, "Validação campo: messagee");
        softAssert.assertEquals(response.body().jsonPath().get("code").toString(), code, "Validação campo: code");
        softAssert.assertAll();
    }

}
