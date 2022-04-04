package com.javarestassuredtemplate.tests.projects;

import com.javarestassuredtemplate.GlobalParameters;
import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.ProjectsDBSteps;
import com.javarestassuredtemplate.requests.projects.PostSubProjectsRequest;
import com.javarestassuredtemplate.steps.PostProjectsSteps;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PostSubProjectsTests extends TestBase {
    PostSubProjectsRequest postSubProjectsRequest;
    SoftAssert softAssert;
    int id;

    @AfterTest
    //Limpando base
    public void afterTesteApagaProject() {
        if (GlobalParameters.ENVIROMENT != "dev") {
            ProjectsDBSteps.apagarProjetos();
        }
    }

    @Test(priority = 1)
    public void cadastrarSubProjetocomSucesso() {
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        String projectName = "Cadastrar Sub Projeto Com Sucesso";
        JsonPath jsonPath = PostProjectsSteps.cadastrarProjetoNovoStep(projectName).body().jsonPath();
        id = jsonPath.get("project.id");

        String projectNameSub = "Cadastrar Sub Projeto Com Sucesso 1";
        PostProjectsSteps.cadastrarProjetoNovoStep(projectNameSub);

        String inherit = "true";
        int statusCodeEsperado = HttpStatus.SC_NO_CONTENT;

        //Fluxo
        postSubProjectsRequest = new PostSubProjectsRequest(id);
        postSubProjectsRequest.setJsonBodyUsingJsonFile(projectNameSub, inherit);
        Response response = postSubProjectsRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
    }

    @Test(priority = 2)
    public void CadastrarSubProjetojaCadastrado() {
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        String projectName = "Cadastrar Sub Projeto Com Sucesso 1";
        String inherit = "1";

        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;
        String code = "704";

        //Fluxo
        postSubProjectsRequest = new PostSubProjectsRequest(id);
        postSubProjectsRequest.setJsonBodyUsingJsonFile(projectName, inherit);
        Response response = postSubProjectsRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("code").toString(), code, "Validação campo: code");
        softAssert.assertAll();
    }

    //Fluxo exceção
    @Test(priority = 3)
    public void CadastrarSubProjetojaCadastradoIdInexistente() {
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        int idInexistente = 999;
        String subProjectName = "Projeto 1 Criado para os testes Automação sub";
        String inherit = "1";

        int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;
        String menssage = "Project '" + idInexistente + "' not found";
        String code = "700";

        //Fluxo
        postSubProjectsRequest = new PostSubProjectsRequest(idInexistente);
        postSubProjectsRequest.setJsonBodyUsingJsonFile(subProjectName, inherit);
        Response response = postSubProjectsRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("message").toString(), menssage, "Validação campo: messagee");
        softAssert.assertEquals(response.body().jsonPath().get("code").toString(), code, "Validação campo: code");
        softAssert.assertAll();
    }
}
