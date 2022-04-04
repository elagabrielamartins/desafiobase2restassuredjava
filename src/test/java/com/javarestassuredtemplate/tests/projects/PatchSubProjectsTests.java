package com.javarestassuredtemplate.tests.projects;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.projects.PatchSubProjectsRequest;
import com.javarestassuredtemplate.requests.projects.PostSubProjectsRequest;
import com.javarestassuredtemplate.steps.PostProjectsSteps;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PatchSubProjectsTests extends TestBase {
    PatchSubProjectsRequest patchSubProjectsRequest;

    @Test
    public void atualizarHerancaSubProjetoSucesso(){
        //Parâmetros
        //Parâmetros
        String projectName = "Atualizar Heranca Sub Projeto Sucesso";
        JsonPath jsonPath = PostProjectsSteps.cadastrarProjetoNovoStep(projectName).body().jsonPath();
        int idProject = jsonPath.get("project.id");

        String projectNameSub = "Atualizar Heranca Sub Projeto Sucesso 1";
        JsonPath jsonPathSubProject = PostProjectsSteps.cadastrarProjetoNovoStep(projectNameSub).body().jsonPath();
        int idSubProject = jsonPathSubProject.get("project.id");

        String inherit = "true";
        int statusCodeEsperado = HttpStatus.SC_NO_CONTENT;

        //Fluxo
        PostSubProjectsRequest postSubProjectsRequest = new PostSubProjectsRequest(idProject);
        postSubProjectsRequest.setJsonBodyUsingJsonFile(projectNameSub, inherit);
        Response responseVincSub = postSubProjectsRequest.executeRequest2();
        Assert.assertEquals(responseVincSub.statusCode(), statusCodeEsperado,"Validacao do Campo: status_code Sub Project");

        String inherit_parent = "0";

        //Fluxo
        patchSubProjectsRequest = new PatchSubProjectsRequest(idProject, idSubProject);
        patchSubProjectsRequest.setJsonBodyUsingJsonFile(projectNameSub, inherit_parent);
        Response response = patchSubProjectsRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado,"Validação do Campo: status_code");
    }
//Fluxo de exceçao
    @Test
    public void naoAtualizarHerancaSubProjetoInexistente(){
        //Parâmetros
        int IDProj = 6;
        int IDSubProj = 9999;
        String subprojectname = "ProjetoTeste007";
        String inherit_parent = "0";
        int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;

        //Fluxo
        patchSubProjectsRequest = new PatchSubProjectsRequest(IDProj, IDSubProj);
        patchSubProjectsRequest.setJsonBodyUsingJsonFile(subprojectname, inherit_parent);
        Response response = patchSubProjectsRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado,"Validação do Campo: status_code");
    }

    @Test
    public void naoAtualizarHerancaProjetoInexistente(){
        //Parâmetros
        int IDProj = 999999999;
        int IDSubProj = 8;
        String subprojectname = "ProjetoName";
        String inherit_parent = "0";
        int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;

        //Fluxo
        patchSubProjectsRequest = new PatchSubProjectsRequest(IDProj, IDSubProj);
        patchSubProjectsRequest.setJsonBodyUsingJsonFile(subprojectname, inherit_parent);
        Response response = patchSubProjectsRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado,"Validação do Campo: status_code");
    }
}
