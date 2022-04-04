package com.javarestassuredtemplate.tests.projects;

import com.javarestassuredtemplate.GlobalParameters;
import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.ProjectsDBSteps;
import com.javarestassuredtemplate.requests.projects.GetProjectsRequest;
import com.javarestassuredtemplate.steps.PostProjectsSteps;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetProjectsTests extends TestBase {

    @Test
    public void consultaProjetoCadastrado() {
        SoftAssert softAssert = new SoftAssert();

        //Fluxo
        String projectName = "Projecto Consulta Cadastrado";
        int id = PostProjectsSteps.cadastrarProjetoNovoStep(projectName).body().jsonPath().get("project.id");

        //GET
        //Parâmetros
        int statusCodeEsperadoGET = HttpStatus.SC_OK;

        //Fluxo
        GetProjectsRequest getProjectsRequest = new GetProjectsRequest(id);
        Response responseGET = getProjectsRequest.executeRequest2();

        //Asserções GET
        Assert.assertEquals(responseGET.statusCode(), statusCodeEsperadoGET, "Validação campo: statusCodeEsperadoGET");
        softAssert.assertEquals(responseGET.body().jsonPath().get("projects.id[0]").toString(), String.valueOf(id), "Validação campo: projects.id[0]");
        softAssert.assertEquals(responseGET.body().jsonPath().get("projects.name[0]").toString(), projectName, "Validação campo: projects.name[0]");
        softAssert.assertAll();
    }

    @Test
    public void consultaProjetoNaoCadastrado() {
        SoftAssert softAssert = new SoftAssert();
        //GET
        //Parâmetros
        int statusCodeEsperadoGET = HttpStatus.SC_NOT_FOUND;
        int ID = -7;

        String message = "Project #-7 not found";
        String code = "700";

        //Fluxo
        GetProjectsRequest getProjectsRequest = new GetProjectsRequest(ID);
        Response response = getProjectsRequest.executeRequest2();

        //Asserções GET
        Assert.assertEquals(response.statusCode(), statusCodeEsperadoGET, "Validação campo: statusCodeEsperado");
        softAssert.assertEquals(response.body().jsonPath().get("message").toString(), message, "Validação campo: message");
        softAssert.assertEquals(response.body().jsonPath().get("code").toString(), code, "Validação campo: code");
        softAssert.assertAll();
    }

    @AfterTest
    public void afterTesteApagaProject() {
        if (GlobalParameters.ENVIROMENT != "dev") {
            ProjectsDBSteps.apagarProjetos();
        }
    }
}
