package com.javarestassuredtemplate.tests.filters;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.FilterDBSteps;
import com.javarestassuredtemplate.dbsteps.ProjectsDBSteps;
import com.javarestassuredtemplate.requests.filters.GetFiltersIDRequest;
import com.javarestassuredtemplate.requests.filters.GetFiltersRequest;
import com.javarestassuredtemplate.steps.PostProjectsSteps;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class GetFiltersTests  extends TestBase {
    SoftAssert softAssert;
    GetFiltersRequest getFiltersRequest;
    GetFiltersIDRequest getFiltersIDRequest;

    @Test
    public void ConsultarFiltrosCadastrado(){
        softAssert = new SoftAssert();

        int idFilter1 = 1;
        String nameFilter1 = "Filtro 1";
        FilterDBSteps.insertFilter(idFilter1, nameFilter1, 0);
        int idFilter2 = 2;
        String nameFilter2 = "Filtro 2";
        FilterDBSteps.insertFilter(idFilter2, nameFilter2, 0);
        //Parâmetros
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        getFiltersRequest = new GetFiltersRequest();
        Response response = getFiltersRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertTrue(response.body().jsonPath().get("filters[0].id").toString() != null);
        softAssert.assertAll();
    }

    @Test
    public void ConsultarFiltroIDCadsatrado(){
        softAssert = new SoftAssert();

        int idFilter = 3;
        String nameFilter = "Filtro 3";
        FilterDBSteps.insertFilter(idFilter, nameFilter, 0);

        //Parâmetros
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        getFiltersIDRequest = new GetFiltersIDRequest(idFilter);
        Response response = getFiltersIDRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("filters[0].id").toString(), String.valueOf(idFilter), "Validação campo: filters[0].id");
        softAssert.assertEquals(response.body().jsonPath().get("filters[0].name").toString(), nameFilter, "Validação campo: filters[0].name");
        softAssert.assertAll();

    }

    @Test
    public void ConsultarFiltroIDNaoCadsatrado(){
        softAssert = new SoftAssert();

        //Parâmetros
        int filterId = 10;
        String name = "TesteFiltro007";
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        getFiltersIDRequest = new GetFiltersIDRequest(filterId);
        Response response = getFiltersIDRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);

    }

    @Test
    public void ConsultaFiltroIDNULL(){
        softAssert = new SoftAssert();

        //Parâmetros
        Integer filterId = null;
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        getFiltersIDRequest = new GetFiltersIDRequest(filterId);
        Response response = getFiltersIDRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);

    }

    @Test
    public void ConsultaFiltroIDZero(){
        softAssert = new SoftAssert();

        //Parâmetros
        int filterId = 0;
        String name = "TesteFiltro003";
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        getFiltersIDRequest = new GetFiltersIDRequest(filterId);
        Response response = getFiltersIDRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);

    }

    @AfterTest
    public void afterTest () {
        ProjectsDBSteps.apagarProjetos();
        FilterDBSteps.apagarFilters();
    }
}
