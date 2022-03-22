package com.javarestassuredtemplate.tests.filters;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.filters.GetFiltersIDRequest;
import com.javarestassuredtemplate.requests.filters.GetFiltersRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class GetFiltersTests  extends TestBase {
    SoftAssert softAssert;
    GetFiltersRequest getFiltersRequest;
    GetFiltersIDRequest getFiltersIDRequest;

    @Test
    public void ConsultarFiltrosCadastrado(){
        softAssert = new SoftAssert();

        //Parâmetros
        String id = "2";
        String name = "TesteFiltro002";
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        getFiltersRequest = new GetFiltersRequest();
        Response response = getFiltersRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("filters[0].id").toString(), id, "Validação campo: filters[0].id");
        softAssert.assertEquals(response.body().jsonPath().get("filters[0].name").toString(), name, "Validação campo: filters[0].name");
        softAssert.assertAll();
    }

    @Test
    public void ConsultarFiltroIDCadsatrado(){
        softAssert = new SoftAssert();

        //Parâmetros
        String filter_id = "3";
        String id = "3";
        String name = "TesteFiltro007";
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        getFiltersIDRequest = new GetFiltersIDRequest(filter_id);
        Response response = getFiltersIDRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("filters[0].id").toString(), id, "Validação campo: filters[0].id");
        softAssert.assertEquals(response.body().jsonPath().get("filters[0].name").toString(), name, "Validação campo: filters[0].name");
        softAssert.assertAll();
    }

    @Test
    public void ConsultarFiltroIDNaoCadsatrado(){
        softAssert = new SoftAssert();

        //Parâmetros
        String filter_id = "10";
        String name = "TesteFiltro007";
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        getFiltersIDRequest = new GetFiltersIDRequest(filter_id);
        Response response = getFiltersIDRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);

    }

    @Test
    public void ConsultaFiltroIDNULL(){
        softAssert = new SoftAssert();

        //Parâmetros
        String filter_id = null;
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        getFiltersIDRequest = new GetFiltersIDRequest(filter_id);
        Response response = getFiltersIDRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);

    }

    @Test
    public void ConsultaFiltroIDZero(){
        softAssert = new SoftAssert();

        //Parâmetros
        String filter_id = "0";
        String name = "TesteFiltro003";
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        getFiltersIDRequest = new GetFiltersIDRequest(filter_id);
        Response response = getFiltersIDRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);

    }

}
