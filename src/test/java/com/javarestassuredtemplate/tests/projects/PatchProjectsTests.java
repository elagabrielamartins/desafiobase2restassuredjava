package com.javarestassuredtemplate.tests.projects;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.projects.PatchProjectsRequest;
import com.javarestassuredtemplate.steps.PostProjectsSteps;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PatchProjectsTests extends TestBase {
    PatchProjectsRequest patchProjectsRequest;

    @Test
    public void atualizarNomeProjetoSucesso(){
        //Parâmetros
        String projectName = "Atualizar Nome Projeto Com Sucesso";
        int idProject = PostProjectsSteps.cadastrarProjetoNovoStep(projectName).body().jsonPath().get("project.id");

        String nameProjectUpdate = projectName + " 1";
        String enabled = "0";
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        patchProjectsRequest = new PatchProjectsRequest(idProject);
        patchProjectsRequest.setJsonBodyUsingJsonFile(String.valueOf(idProject), nameProjectUpdate, enabled);
        Response response = patchProjectsRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado,"Validacao do Campo: status_code");
    }

    @Test
    public void naoAtualizarprojetoInexistente(){
        //Parâmetros
        int ID = 999999;
        String idProjects = "999999";
        String nameProjects = "Projeto Não Encontrado";
        String enabled = "0";
        int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;

        //Fluxo
        patchProjectsRequest = new PatchProjectsRequest(ID);
        patchProjectsRequest.setJsonBodyUsingJsonFile(idProjects, nameProjects, enabled);
        Response response = patchProjectsRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado,"Validacao do Campo: status_code");
    }


}
