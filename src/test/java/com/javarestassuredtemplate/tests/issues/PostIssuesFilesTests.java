package com.javarestassuredtemplate.tests.issues;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.Issues.PostIssuesFilesRequest;
import com.javarestassuredtemplate.steps.PostIssuesSteps;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostIssuesFilesTests extends TestBase {
    PostIssuesSteps postIssuesSteps;
    PostIssuesFilesRequest postIssuesFilesRequest;


    @Test
    public void  criarBugComAnexo()  {
        //Chamadas
        postIssuesSteps = new PostIssuesSteps();

        //Parâmetros
        JsonPath issueBugResponse = postIssuesSteps.cadastrarBugIssuesSteps();
        int issues_id = issueBugResponse.get("issue.id");
        String name = "test.txt";
        String content = "dwNYF0byh&TSG*vzjoYwZL!yYIedU8fpAxbJ6pku2k$uvLtXfudwNYF0byh&TSG*vzjoYwZL!yYIedU8fpAxbJ6pku2k$uvLtXfu=";
        int statusCodeEsperado = HttpStatus.SC_CREATED;

        //Fluxo
        postIssuesFilesRequest = new PostIssuesFilesRequest(issues_id);
        postIssuesFilesRequest.setJsonBodyUsingJsonFile(name,content);
        Response response = postIssuesFilesRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
    }

}