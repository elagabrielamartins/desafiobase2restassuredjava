package com.javarestassuredtemplate.tests.projects;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.projects.DelSubProjectsRequest;
import com.javarestassuredtemplate.requests.projects.PostSubProjectsRequest;
import com.javarestassuredtemplate.steps.PostProjectsSteps;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DelSubProjectsTests  extends TestBase {
    DelSubProjectsRequest delSubProjectsRequest;

    @Test
    public void deletaSubProjetocomSucesso(){
        //Parâmetros
        String projectName = "Delete Sub Projeto Com Sucesso";
        JsonPath jsonPath = PostProjectsSteps.cadastrarProjetoNovoStep(projectName).body().jsonPath();
        int idProject = jsonPath.get("project.id");

        String projectNameSub = "Delete Sub Projeto Com Sucesso 1";
        JsonPath jsonPathSubProject = PostProjectsSteps.cadastrarProjetoNovoStep(projectNameSub).body().jsonPath();
        int idSubProject = jsonPathSubProject.get("project.id");

        String inherit = "true";
        int statusCodeEsperado = HttpStatus.SC_NO_CONTENT;

        //Fluxo
        PostSubProjectsRequest postSubProjectsRequest = new PostSubProjectsRequest(idProject);
        postSubProjectsRequest.setJsonBodyUsingJsonFile(projectNameSub, inherit);
        Response responseVincSub = postSubProjectsRequest.executeRequest2();
        Assert.assertEquals(responseVincSub.statusCode(), statusCodeEsperado,"Validacao do Campo: status_code Sub Project");

        //Fluxo
        delSubProjectsRequest = new DelSubProjectsRequest(idProject, idSubProject);
        Response response = delSubProjectsRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado,"Validacao do Campo: status_code");
    }

    @Test
    public void naoDeletaSubProjetoInexistente(){
        //Parâmetros
        int IDProject =  9;
        int IDSubProject = 999999;
        int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;

        //Fluxo
        delSubProjectsRequest = new DelSubProjectsRequest(IDProject, IDSubProject);
        Response response = delSubProjectsRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado,"Validacao do Campo: status_code");
    }
}
