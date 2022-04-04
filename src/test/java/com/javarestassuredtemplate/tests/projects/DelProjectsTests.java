package com.javarestassuredtemplate.tests.projects;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.projects.DelProjectsRequest;
import com.javarestassuredtemplate.steps.PostProjectsSteps;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DelProjectsTests extends TestBase {
    DelProjectsRequest delProjectsRequest;

    @Test
    public void deletaProjetocomSucesso(){

        String projectName = "Deleta Projeto Com Sucesso";
        int id = PostProjectsSteps.cadastrarProjetoNovoStep(projectName).body().jsonPath().get("project.id");
        //Parâmetros
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        delProjectsRequest = new DelProjectsRequest(id);
        Response response = delProjectsRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado,"Validacao do Campo: status_code");
    }

    @Test
    public void naoDeleterProjetoInexistente(){
        //Parâmetros
        int ID = 999999999;
        int statusCodeEsperado = HttpStatus.SC_FORBIDDEN;

        //Fluxo
        delProjectsRequest = new DelProjectsRequest(ID);
        Response response = delProjectsRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado,"Validacao do Campo: status_code");
    }

}
