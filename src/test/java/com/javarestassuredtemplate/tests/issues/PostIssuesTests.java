package com.javarestassuredtemplate.tests.issues;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.Issues.PostIssuesRequest;
import com.javarestassuredtemplate.steps.PostIssuesSteps;
import com.javarestassuredtemplate.steps.PostProjectsSteps;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PostIssuesTests extends TestBase {
    SoftAssert softAssert;
    PostIssuesRequest postIssuesRequest;

    @Test
    public void cadastrarBugcomSucesso()  {
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        String summary = "Teste Cadastro de bug";
        String description = "Utilizado para Automatizar os testes";
        String categoryName = "General";

        String projectName = "Cadastrar Bug Com Sucesso";
        PostProjectsSteps.cadastrarProjetoNovoStep(projectName).body().jsonPath();

        int statusCodeEsperado = HttpStatus.SC_CREATED;

        //Fluxo
        postIssuesRequest = new PostIssuesRequest();
        postIssuesRequest.setJsonBodyUsingJsonFile(summary, description, categoryName, projectName);
        Response response = postIssuesRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("issue.description").toString(), description, "Validação campo: issue.description");
        softAssert.assertAll();

    }

    @Test
    public void naoCadastrarBugcomProjetoInexistente()  {
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        String summary = "Teste Cadastro de bug";
        String description = "Utilizado para Automatizar os testes";
        String category_name = "General";
        String project_name = "Projeto 999999";
        String message = "Project not specified";
        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;

        //Fluxo
        postIssuesRequest = new PostIssuesRequest();
        postIssuesRequest.setJsonBodyUsingJsonFile(summary, description, category_name, project_name);
        Response response = postIssuesRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("message").toString(), message, "Validação campo: message");
        softAssert.assertAll();

    }
    @Test
    public void naoCadastrarBugSemCategoria()  {
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        String summary = "Teste Cadastro de bug";
        String description = "Utilizado para Automatizar os testes";
        String category_name = "";
        String project_name = "Projeto 999999";
        String message = "Project not specified";
        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;

        //Fluxo
        postIssuesRequest = new PostIssuesRequest();
        postIssuesRequest.setJsonBodyUsingJsonFile(summary, description, category_name, project_name);
        Response response = postIssuesRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("message").toString(), message, "Validação campo: message");
        softAssert.assertAll();

    }
    @Test
    public void naoCadastrarBugSemDescricao()  {
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        String summary = "Teste Cadastro de bug";
        String description = "";
        String category_name = "General";
        String project_name = "Projeto 999999";
        String message = "Description not specified";
        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;

        //Fluxo
        postIssuesRequest = new PostIssuesRequest();
        postIssuesRequest.setJsonBodyUsingJsonFile(summary, description, category_name, project_name);
        Response response = postIssuesRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("message").toString(), message, "Validação campo: message");
        softAssert.assertAll();

    }
    @Test
    public void naoCadastrarBugSemNomeSummary()  {
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        String summary = "";
        String description = "Teste cadastro de bug sem summary";
        String category_name = "General";
        String project_name = "Projeto 999999";
        String message = "Summary not specified";
        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;

        //Fluxo
        postIssuesRequest = new PostIssuesRequest();
        postIssuesRequest.setJsonBodyUsingJsonFile(summary, description, category_name, project_name);
        Response response = postIssuesRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("message").toString(), message, "Validação campo: message");
        softAssert.assertAll();

    }


}