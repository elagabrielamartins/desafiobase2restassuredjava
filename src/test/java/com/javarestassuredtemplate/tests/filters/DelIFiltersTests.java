package com.javarestassuredtemplate.tests.filters;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.FilterDBSteps;
import com.javarestassuredtemplate.requests.filters.DelIFiltersRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DelIFiltersTests extends TestBase {
    SoftAssert softAssert;
    DelIFiltersRequest delIFiltersRequest;

    @Test
    public void deletarFiltroComSucesso(){
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        int statusCodeEsperado01 = HttpStatus.SC_NO_CONTENT;
        int idFilter = 4;
        String nameFilter = "Filtro 4";
        FilterDBSteps.insertFilter(idFilter, nameFilter, 0);

        //Fluxo
        delIFiltersRequest = new DelIFiltersRequest(String.valueOf(idFilter));
        Response response = delIFiltersRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado01);
    }

    @Test
    public void deletarFiltroIDInvalido(){
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        int statusCodeEsperado01 = HttpStatus.SC_NOT_FOUND;
        Integer idFilter = 14;

        //Fluxo
        delIFiltersRequest = new DelIFiltersRequest(String.valueOf(idFilter));
        Response response = delIFiltersRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado01);
    }

    @Test
    public void deletarFiltroIDnulo(){
        //Chamadas
        softAssert = new SoftAssert();

        //Parâmetros
        int statusCodeEsperado01 = HttpStatus.SC_METHOD_NOT_ALLOWED;
        String idFilter = "";

        //Fluxo
        delIFiltersRequest = new DelIFiltersRequest(String.valueOf(idFilter));
        Response response = delIFiltersRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado01);
    }

}