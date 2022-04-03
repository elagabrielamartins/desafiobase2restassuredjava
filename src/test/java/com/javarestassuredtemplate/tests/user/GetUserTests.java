package com.javarestassuredtemplate.tests.user;

import com.javarestassuredtemplate.bases.TestBase;
//import com.javarestassuredtemplate.dbsteps.UserDBSteps;
import com.javarestassuredtemplate.dbsteps.UserDBSteps;
import com.javarestassuredtemplate.requests.user.GetUserRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

public class GetUserTests extends TestBase {
    GetUserRequest getUserRequest;
    SoftAssert softAssert;

    @Test
    public void buscarUsuarioAdministrador(){
        softAssert = new SoftAssert();

        //Parâmetros
        String name = "administrator";
        String email = "root@localhost";

        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        getUserRequest = new GetUserRequest();
        Response response  = getUserRequest.executeRequest2();


        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("name").toString(), name, "Validação name retorno consulta");
        softAssert.assertEquals(response.body().jsonPath().get("email").toString(), email, "Validação email");
        softAssert.assertAll();

    }

    @Test
    public void buscarUsuarioADMviaQuery()  {
        softAssert = new SoftAssert();
        //Parâmetros
        ArrayList<String> consultaUsuarioADM =  UserDBSteps.consultaUsuarioADM();
        String id = consultaUsuarioADM.get(0);
        String userName = consultaUsuarioADM.get(1);
        String email = consultaUsuarioADM.get(2);
        String acessLevel = consultaUsuarioADM.get(3);
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        getUserRequest = new GetUserRequest();
        Response response  = getUserRequest.executeRequest2();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("id").toString(), id, "Validação id retorno consulta");
        softAssert.assertEquals(response.body().jsonPath().get("name").toString(), userName, "Validação name retorno consulta");
        softAssert.assertEquals(response.body().jsonPath().get("email").toString(), email, "Validação email");
        softAssert.assertEquals(response.body().jsonPath().get("access_level.id").toString(), acessLevel, "Validação nivel de acesso retorno consulta");
        softAssert.assertAll();
    }

}
