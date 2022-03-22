package com.javarestassuredtemplate.tests.issues;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.Issues.GetIssuesRequest;
import com.javarestassuredtemplate.steps.PostIssuesSteps;
import com.javarestassuredtemplate.steps.PostProjectsSteps;
import com.javarestassuredtemplate.utils.JsonPathList;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetIssuesTests extends TestBase {
    SoftAssert softAssert;
    PostIssuesSteps postIssuesSteps;
    GetIssuesRequest getIssuesRequest;


    @Test
    public void ConsultaBugComSucesso()  {
        //Chamadas
        softAssert = new SoftAssert();
        postIssuesSteps = new PostIssuesSteps();

        //Parâmetros

        JsonPath issueBugResponse = postIssuesSteps.cadastrarBugIssuesSteps();

        String issues_project_name = issueBugResponse.get("issue.project.name");
        String issues_id = issueBugResponse.getString("issue.id");
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        getIssuesRequest = new GetIssuesRequest();
        getIssuesRequest.addPathVariable(issues_id);
        Response response = getIssuesRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);

        softAssert.assertEquals(JsonPathList.getInt(response, "issues.id"), issues_id, "Validação campo: issues.id");
        softAssert.assertEquals(JsonPathList.getString(response, "issues.project.name"), issues_project_name, "Validação campo: issues.project.name");
        softAssert.assertAll();
    }
    @Test
    public void ConsultaBugListaComSucesso()  {
        //Chamadas
        softAssert = new SoftAssert();
        postIssuesSteps = new PostIssuesSteps();

        //Parâmetros
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        getIssuesRequest = new GetIssuesRequest();
        getIssuesRequest.addQueryParameters("page_size","10").addQueryParameters("page","1");
        Response response = getIssuesRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertAll();
    }
//
//    @Test
//    public void ConsultaBugIdInexistente()  {
//        //Chamadas
//        softAssert = new SoftAssert();
//        postIssuesSteps = new PostIssuesSteps();
//
//        //Parâmetros
//        int idProjectOld = postIssuesSteps.cadastrarBugIssues();
//        int idProject = idProjectOld + 100;
//        String issues_id = Integer.toString(idProject);
//        String message = "Issue #" +issues_id+ " not found";
//        int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;
//
//        //Fluxo
//        getIssuesRequest = new GetIssuesRequest(idProject);
//        Response response = getIssuesRequest.executeRequest2();
//
//        //Asserções
//        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
//        softAssert.assertEquals(response.body().jsonPath().get("message").toString(), message, "Validação campo: message");
//        softAssert.assertAll();
//    }


}