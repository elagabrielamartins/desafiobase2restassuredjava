package com.javarestassuredtemplate.tests.projects;

import com.javarestassuredtemplate.GlobalParameters;
import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.ProjectsDBSteps;
import com.javarestassuredtemplate.requests.projects.PostProjectsRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class PostProjectsTests extends TestBase {
    PostProjectsRequest postProjectsRequest;
    SoftAssert softAssert;

    @AfterTest
//Limpando base
    public void afterTesteApagaProject() {
        if (GlobalParameters.ENVIROMENT != "dev") {
            ProjectsDBSteps.apagarProjetos();
        }
    }

    @Test
    public void cadastrarProjetoNovocomSucesso() {
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        String projectName = "Cadastrar Projeot Novo Com Sucesso";
        int statusID = 50;
        String statusName = "stable";
        String descriptionText = "Projeto Cadastrar Projeot Novo Com Sucesso Criado para os testes Automação";
        int enableID = 0;
        int viewID = 10;
        String viewName = "public";
        int statusCodeEsperado = HttpStatus.SC_CREATED;

        //Fluxo
        postProjectsRequest = new PostProjectsRequest();
        postProjectsRequest.setJsonBodyUsingJsonFile(projectName, statusID, statusName, descriptionText, enableID, viewID, viewName);
        Response response = postProjectsRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("project.name").toString(), projectName, "Validação campo: project.name");
        softAssert.assertEquals(response.body().jsonPath().get("project.status.id").toString(), String.valueOf(statusID), "Validação campo: user.realName");
        softAssert.assertEquals(response.body().jsonPath().get("project.status.name").toString(), statusName, "Validação campo: project.status.name");
        softAssert.assertEquals(response.body().jsonPath().get("project.view_state.id").toString(), String.valueOf(viewID), "Validação campo: project.view_state.id");
        softAssert.assertEquals(response.body().jsonPath().get("project.view_state.name").toString(), "public", "Validação campo: project.view_state.name");
        softAssert.assertAll();


    }

    //    Para esse teste quando roda a primeira vez o Status Code é 201, na segunda vez que roda, é status Code 200 (Porém nao cria, e nao  atualiza, acredito que esta com bug!)
    @Test
    public void cadastrarProjetoNomeIgual() {
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        String projectName = "Projeto 001 Criado para os testes Automação";
        int statusID = 50;
        String statusName = "";
        String descriptionText = "Projeto Tentativa de Cadastro Nome Igual ";
        int enableID = 0;
        int viewID = 10;
        String viewName = "";
        int statusCodeEsperado = HttpStatus.SC_CREATED;

        //Fluxo
        postProjectsRequest = new PostProjectsRequest();
        postProjectsRequest.setJsonBodyUsingJsonFile(projectName, statusID, statusName, descriptionText, enableID, viewID, viewName);
        Response response = postProjectsRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("project.name").toString(), projectName, "Validação campo: project.name");
        softAssert.assertAll();

    }

    @Test
    public void CadastrarProjetoSemNome() {
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        String projectName = "";
        int statusID = 50;
        String statusName = "stable";
        String descriptionText = "Projeto Tentativa de Cadastro Nome Igual";
        int enableID = 0;
        int viewID = 10;
        String viewName = "public";
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        postProjectsRequest = new PostProjectsRequest();
        postProjectsRequest.setJsonBodyUsingJsonFile(projectName, statusID, statusName, descriptionText, enableID, viewID, viewName);
        Response response = postProjectsRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertTrue(response.body().htmlPath().get().toString().contains("Fatal error"), "Validação campo: HTML");
        softAssert.assertEquals(response.body().htmlPath().get().toString(), "nullFatal error/var/www/html/core/project_api.php341null", "Validação campo: HTML");
        softAssert.assertAll();

    }

}
