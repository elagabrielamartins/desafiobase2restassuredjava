package com.javarestassuredtemplate.steps;

import com.javarestassuredtemplate.requests.projects.PostProjectsRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

public class PostProjectsSteps {

        public static Response cadastrarProjetoNovoStep(String projectName) {

            //Parâmetros
            int statusID = 50;
            String statusName = "stable";
            String descriptionText = "Projeto 007 Criado para os testes Automação";
            int enableID = 0;
            int viewID = 10;
            String viewName = "public";
            int statusCodeEsperado = HttpStatus.SC_CREATED;

            //Fluxo
            PostProjectsRequest postProjectsRequest = new PostProjectsRequest();
            postProjectsRequest.setJsonBodyUsingJsonFile(projectName, statusID, statusName, descriptionText, enableID, viewID, viewName);
            return postProjectsRequest.executeRequest2();

        }
}