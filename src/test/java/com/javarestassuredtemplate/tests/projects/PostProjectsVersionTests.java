package com.javarestassuredtemplate.tests.projects;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.projects.PostProjectsVersionRequest;
import com.javarestassuredtemplate.steps.PostProjectsSteps;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PostProjectsVersionTests extends TestBase {
    PostProjectsVersionRequest postProjectsVersionRequest;
    SoftAssert softAssert;

    @Test
    public void cadastrarVersaoProjeto() {
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        String projectName = "Cadastrar Versao No Projeto";
        cadastrarProjetoEVersao(projectName);
    }

    @Test
    public void CadastrarVersaoIgual() {
        //Chamadas
        softAssert = new SoftAssert();

        String projectName = "Cadastrar Versao Igual";
        int idProject = cadastrarProjetoEVersao(projectName);
        Response response = cadastrarVersaoNoProjetos(idProject);
        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;
        String menssage = "Version 'V.1.99.77' already exists";
        String code = "1600";

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("message").toString(), menssage, "Validação campo: messagee");
        softAssert.assertEquals(response.body().jsonPath().get("code").toString(), code, "Validação campo: code");
        softAssert.assertAll();

    }

    @Test
    public void CadastrarVersaoVazia() {
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        int ID = 1;
        String projectVersion = "";
        String projectDescription = "Versao Testes API";
        String projectRelease = "true";
        String projectObsolete = "true";
        String projectDate = "2022-03-18";
        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;
        String menssage = "Invalid version name";
        String code = "11";

        //Fluxo
        postProjectsVersionRequest = new PostProjectsVersionRequest(ID);
        postProjectsVersionRequest.setJsonBodyUsingJsonFile(projectVersion, projectDescription, projectRelease, projectObsolete, projectDate);
        Response response = postProjectsVersionRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("message").toString(), menssage, "Validação campo: messagee");
        softAssert.assertEquals(response.body().jsonPath().get("code").toString(), code, "Validação campo: code");
        softAssert.assertAll();

    }

    private int cadastrarProjetoEVersao(String projectName) {
        int idProject = PostProjectsSteps.cadastrarProjetoNovoStep(projectName).body().jsonPath().get("project.id");

        Response response = cadastrarVersaoNoProjetos(idProject);

        int statusCodeEsperado = HttpStatus.SC_NO_CONTENT;
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);

        return idProject;
    }

    private Response cadastrarVersaoNoProjetos(int idProject) {
        String projectVersion = "V.1.99.77";
        String projectDescription = "Versao Testes API";
        String projectRelease = "true";
        String projectObsolete = "true";
        String projectDate = "2022-03-18";

        //Fluxo
        postProjectsVersionRequest = new PostProjectsVersionRequest(idProject);
        postProjectsVersionRequest.setJsonBodyUsingJsonFile(projectVersion, projectDescription, projectRelease, projectObsolete, projectDate);
        return postProjectsVersionRequest.executeRequest2();
    }
}