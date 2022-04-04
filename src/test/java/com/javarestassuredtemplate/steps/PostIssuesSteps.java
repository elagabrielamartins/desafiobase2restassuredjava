package com.javarestassuredtemplate.steps;

import com.javarestassuredtemplate.requests.Issues.PostIssuesRequest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;

public class PostIssuesSteps {

    public static JsonPath cadastrarBugIssuesSteps()  {
        //Chamadas
        PostIssuesRequest postIssuesRequest;

        //Parâmetros
        String summary = "Teste Cadastro de Issues";
        String description = "Utilizado para Issues e Complementos";
        String categoryName = "General";
        String projectName = "Cadastrar Bug Issues Steps";
        PostProjectsSteps.cadastrarProjetoNovoStep( projectName);
        int statusCodeEsperado = HttpStatus.SC_CREATED;

        //Fluxo
        postIssuesRequest = new PostIssuesRequest();
        postIssuesRequest.setJsonBodyUsingJsonFile(summary, description, categoryName, projectName);
        Response response = postIssuesRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);

        //Retornos
         return response.body().jsonPath();

    }


}