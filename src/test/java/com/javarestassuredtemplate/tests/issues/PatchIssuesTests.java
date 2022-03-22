package com.javarestassuredtemplate.tests.issues;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.Issues.PatchIssuesRequest;
import com.javarestassuredtemplate.steps.PostIssuesSteps;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PatchIssuesTests extends TestBase {
    SoftAssert softAssert;
    PostIssuesSteps postIssuesSteps;
    PatchIssuesRequest patchIssuesRequest;

    @Test
    public void atualizarStatusdoBug(){
        //Chamadas
        softAssert = new SoftAssert();
        postIssuesSteps = new PostIssuesSteps();

        //Parâmetros
        JsonPath issueBugResponse = postIssuesSteps.cadastrarBugIssuesSteps();
        int issues_id = issueBugResponse.get("issue.id");
        String status_name = "assigned";
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        patchIssuesRequest = new PatchIssuesRequest(issues_id);
        patchIssuesRequest.setJsonBodyUsingJsonFile(status_name);
        Response response = patchIssuesRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);

    }

    @Test
    public void atualizarPrioridadeDoBug(){
        //Chamadas
        softAssert = new SoftAssert();
        postIssuesSteps = new PostIssuesSteps();

        //Parâmetros
        JsonPath issueBugResponse = postIssuesSteps.cadastrarBugIssuesSteps();
        int issues_id = issueBugResponse.get("issue.id");
        String status_name = "assigned";
        String prioridade = "high";
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        patchIssuesRequest = new PatchIssuesRequest(issues_id);
        patchIssuesRequest.setJsonBodyUpdatePriority(prioridade,status_name);
        Response response = patchIssuesRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);

    }

    @Test
    public void naoAtualizarStatusBugIDInvalido(){
        //Chamadas
        softAssert = new SoftAssert();
        postIssuesSteps = new PostIssuesSteps();

        //Parâmetros
        int issues_id =  1213141516;
        String status_name = "assigned";
        int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;

        //Fluxo
        patchIssuesRequest = new PatchIssuesRequest(issues_id);
        patchIssuesRequest.setJsonBodyUsingJsonFile(status_name);
        Response response = patchIssuesRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);

    }
}
